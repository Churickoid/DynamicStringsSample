package ru.mail.cloud.resources.cache.fresco

import android.content.Context
import android.graphics.Bitmap

interface DynamicDrawableLoader {
    suspend fun getImageByUrl(url: String, context: Context): Bitmap

    fun getImageFromCache(url: String, context: Context): Bitmap?
}