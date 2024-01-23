package ru.mail.cloud.resources.replacer

import android.graphics.drawable.Drawable
import android.icu.text.PluralRules
import android.os.Build
import ru.mail.cloud.resources.cache.ResourcesMemcache
import ru.mail.cloud.resources.models.DensityName.Companion.getDensityNameByValue
import ru.mail.cloud.resources.models.LanguageName
import ru.mail.cloud.resources.models.ResourceName

class ResourcesReplacerImpl(
    private val resourcesMemcache: ResourcesMemcache,
) : ResourcesReplacer {

    override fun getString(language: LanguageName, resourceIdName: ResourceName): String? =
        resourcesMemcache.getText(language, resourceIdName)

    override fun getQuantityString(
        language: LanguageName,
        rule: PluralRules,
        resourceIdName: ResourceName,
        quantity: Int,
    ): String? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resourcesMemcache.getQuantityText(
            language,
            rule.select(quantity.toDouble()),
            resourceIdName
        )
    } else {
        null
    }

    override fun getDrawable(
        language: LanguageName,
        resourceIdName: ResourceName,
        density: Float,
        defaultDrawableProvider: () -> Drawable,
    ): Drawable =
        resourcesMemcache.getDrawable(
            language,
            resourceIdName,
            getDensityNameByValue(density),
            defaultDrawableProvider
        )

}
