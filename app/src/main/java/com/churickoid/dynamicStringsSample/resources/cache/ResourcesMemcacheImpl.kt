package ru.mail.cloud.resources.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
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

    private val urlsCache: MutableMap<ResourceName, Map<DensityName, String>> =
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
    ): Drawable {
        val resourceUrl = urlsCache[resourceName]?.get(densityName)

        val defaultBitmap = resourceUrl?.let { _ ->
            getBitmapByDrawable(defaultDrawableProvider.invoke())
        }

        val drawable = defaultBitmap?.let {
            val newBitmap = dynamicDrawableLoader.getImageFromCache(resourceUrl, context)
            newBitmap?.let { BitmapDrawable(context.resources, newBitmap) } ?:
            FrescoDrawable(context, scope, dynamicDrawableLoader, resourceUrl, defaultBitmap)
        }
        return drawable ?: defaultDrawableProvider.invoke()

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
            if (!urlsCache.containsKey(key) || urlsCache[key] != value) {
                urlsCache[key] = value
            }
        }
    }

    private fun getBitmapByDrawable(drawable: Drawable): Bitmap? =
        when (drawable) {
            is BitmapDrawable -> {
                drawable.bitmap
            }

            else -> {
                drawable.toBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight
                )
            }
        }

}