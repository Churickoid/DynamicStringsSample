package ru.mail.cloud.resources.cache

import ru.mail.cloud.resources.models.DensityName
import ru.mail.cloud.resources.models.LanguageName
import ru.mail.cloud.resources.models.ResourceName

interface UpdateResourcesMemcache {

    fun updateStrings(strings: Map<ResourceName, Map<LanguageName, String>>)

    fun updatePlurals(plurals: Map<ResourceName, Map<LanguageName, Map<String, String>>>)

    fun updateImages(images: Map<ResourceName, Map<DensityName, String>>)
}