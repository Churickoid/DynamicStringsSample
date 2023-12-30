package com.churickoid.dynamicStringsSample.fresco

import android.net.Uri
import com.facebook.cache.common.SimpleCacheKey
import ru.mail.cloud.library.cache.fresco.CacheListChoice

class StoreCacheKey(key: String, val cacheSaveChooser: CacheListChoice) : SimpleCacheKey(key) {

    override fun containsUri(uri: Uri): Boolean {
        return super.containsUri(uri.buildUpon().clearQuery().build())
    }
}
