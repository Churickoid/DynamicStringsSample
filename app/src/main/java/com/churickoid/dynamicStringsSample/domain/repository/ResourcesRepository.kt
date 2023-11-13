package com.churickoid.dynamicStringsSample.domain.repository

import android.content.res.Resources
import com.churickoid.dynamicStringsSample.domain.entity.ResourceKey
import com.churickoid.dynamicStringsSample.domain.entity.ResourceString
import com.churickoid.dynamicStringsSample.domain.entity.ResourceValue
import java.util.Locale

interface ResourcesRepository {

    var locale: Locale // временно

    fun putResource(resId: Int, value: ResourceValue)

    fun getString(resId: Int): String?

    fun getQuantityString(resId: Int, quantity: String): String

    fun enableFeature(locale: Locale)
}