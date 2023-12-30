package ru.mail.cloud.resources.cache

import android.graphics.drawable.Drawable
import ru.mail.cloud.resources.models.DensityName
import ru.mail.cloud.resources.models.LanguageName
import ru.mail.cloud.resources.models.ResourceName

interface ResourcesMemcache {
    fun getText(language: LanguageName, resourceName: ResourceName): String?

    fun getQuantityText(
        language: LanguageName,
        quantity: String,
        resourceName: ResourceName,
    ): String?

    fun getDrawable(
        language: LanguageName,
        resourceName: ResourceName,
        densityName: DensityName,
        defaultDrawableProvider: () -> Drawable,
    ): Drawable?
}