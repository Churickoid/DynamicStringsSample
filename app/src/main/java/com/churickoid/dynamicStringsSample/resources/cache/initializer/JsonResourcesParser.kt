package ru.mail.cloud.resources.cache.initializer

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.mail.cloud.resources.cache.UpdateResourcesMemcache
import ru.mail.cloud.resources.models.DensityName
import ru.mail.cloud.resources.models.LanguageName
import ru.mail.cloud.resources.models.ResourceName

class JsonResourcesParser(private val udpateCache: UpdateResourcesMemcache) {
    fun fillStringResourcesMemcache(json: String) {
        try {
            val jsonConfig = Json {
                isLenient = true
                ignoreUnknownKeys = true
                serializersModule = SerializersModule {
                    polymorphic(JsonResources::class) {
                        subclass(StringJsonResources::class)
                        subclass(PluralsJsonResources::class)
                    }
                }
            }
            val replacements = jsonConfig
                .decodeFromString<List<JsonResources>>(json)
                .filter { it.enabled }

            replacements
                .filterIsInstance<StringJsonResources>()
                .associate {
                    ResourceName(it.name) to it.replacements
                        .associate { (locale, value) ->
                            LanguageName(locale) to value
                        }
                }
                .also(udpateCache::updateStrings)

            replacements
                .filterIsInstance<PluralsJsonResources>()
                .associate {
                    ResourceName(it.name) to it.replacements
                        .associate { (locale, value) ->
                            LanguageName(locale) to value
                                .associate { plurals -> plurals.quantity to plurals.value }
                        }
                }
                .also(udpateCache::updatePlurals)
        } catch (e: Throwable) {

        }
    }

    fun fillDrawableResourcesMemcache(json: String) {
        try {
            val jsonConfig = Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
            val replacements = jsonConfig
                .decodeFromString<List<DrawableJsonResources>>(json)
                .filter { it.enabled }

            replacements
                .associate {
                    ResourceName(it.name) to it.replacements
                        .associate { (density, url) ->
                            DensityName(density) to url
                        }
                }
                .also(udpateCache::updateImages)
        } catch (e: Throwable) {

        }
    }
}

