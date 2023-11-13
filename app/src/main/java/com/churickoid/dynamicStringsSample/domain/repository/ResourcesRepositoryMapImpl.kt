package com.churickoid.dynamicStringsSample.domain.repository

import com.churickoid.dynamicStringsSample.domain.entity.ResourceString
import com.churickoid.dynamicStringsSample.domain.entity.ResourceValue
import dagger.Provides
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton


class ResourcesRepositoryMapImpl @Inject constructor() : ResourcesRepository {
    private val resourcesMap = mutableMapOf<Int, ResourceValue>()

    // мапа по id
    // мапа по тексту

    private var isEnabled = false

    override lateinit var locale: Locale //временно

    override fun putResource(resId: Int, value: ResourceValue) {
        if (!resourcesMap.contains(resId)) resourcesMap[resId] = value
    }

    override fun getString(resId: Int): String? {

        return if (isEnabled) (resourcesMap[resId] as? ResourceString)?.value
        else null

    }

    override fun getQuantityString(resId: Int, quantity: String): String {
        TODO("Not yet implemented")
    }


    override fun enableFeature(locale: Locale) {
        isEnabled = true
        this.locale = locale
    }

}