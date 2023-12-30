package ru.mail.cloud.resources.cache

import android.content.Context
import android.graphics.drawable.Drawable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.plus
import ru.mail.cloud.resources.cache.fresco.DynamicDrawableLoader
import ru.mail.cloud.resources.cache.fresco.FrescoDrawable
import ru.mail.cloud.resources.models.DensityName
import ru.mail.cloud.resources.models.LanguageName
import ru.mail.cloud.resources.models.ResourceName

class ResourcesMemcacheImpl(
    private val context: Context,
    private val dynamicDrawableLoader: DynamicDrawableLoader,
    coroutineScope: CoroutineScope,
) : ResourcesMemcache, UpdateResourcesMemcache {

    private val supervisorJob = SupervisorJob()
    private val scope = coroutineScope + supervisorJob
    private val stringsCache: MutableMap<ResourceName, Map<LanguageName, String>> =
        mutableMapOf()

    private val pluralsCache: MutableMap<ResourceName, Map<LanguageName, Map<String, String>>> =
        mutableMapOf()

    private val imagesCache: MutableMap<ResourceName, Map<DensityName, String>> =
        mutableMapOf()

    override fun getText(language: LanguageName, resourceName: ResourceName): String? =
        stringsCache.getOrElse(resourceName) { null }?.let {
            it.getOrElse(language) { null }
        }

    override fun getQuantityText(
        language: LanguageName,
        quantity: String,
        resourceName: ResourceName,
    ): String? =
        pluralsCache.getOrElse(resourceName) { null }?.let { resource ->
            resource.getOrElse(language) { null }?.let { language ->
                language.getOrElse(quantity) { null }
            }
        }

    override fun getDrawable(
        language: LanguageName,
        resourceName: ResourceName,
        densityName: DensityName,
        defaultDrawableProvider: () -> Drawable,
    ): Drawable? {
        val url = imagesCache[resourceName]?.let { resource ->
            resource[densityName]
        }
        return url?.let {
            FrescoDrawable(context,scope, dynamicDrawableLoader, url, defaultDrawableProvider)
        }
    }

    override fun updateStrings(strings: Map<ResourceName, Map<LanguageName, String>>) {
        strings.forEach { (key, value) ->
            if (!stringsCache.containsKey(key) || stringsCache[key] != value) {
                stringsCache[key] = value
            }
        }
    }

    override fun updatePlurals(plurals: Map<ResourceName, Map<LanguageName, Map<String, String>>>) {
        plurals.forEach { (key, value) ->
            if (!pluralsCache.containsKey(key) || pluralsCache[key] != value) {
                pluralsCache[key] = value
            }
        }
    }

    override fun updateImages(images: Map<ResourceName, Map<DensityName, String>>) {
        supervisorJob.cancelChildren()
        images.forEach { (key, value) ->
            if (!imagesCache.containsKey(key) || imagesCache[key] != value) {
                imagesCache[key] = value
            }
        }
    }
}