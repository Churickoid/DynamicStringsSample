package ru.mail.cloud.resources.replacer

import android.graphics.drawable.Drawable
import android.icu.text.PluralRules
import ru.mail.cloud.resources.models.DensityName
import ru.mail.cloud.resources.models.LanguageName
import ru.mail.cloud.resources.models.ResourceName

interface ResourcesReplacer {
    fun getString(language: LanguageName, resourceIdName: ResourceName): String?

    fun getQuantityString(
        language: LanguageName,
        rule: PluralRules,
        resourceIdName: ResourceName,
        quantity: Int,
    ): String?

    fun getDrawable(
        resourceIdName: ResourceName,
        densityName: DensityName,
        defaultDrawableProvider: () -> Drawable,
    ): Drawable
}