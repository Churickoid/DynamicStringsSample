package com.churickoid.dynamicStringsSample.fresco

import android.net.Uri
import com.churickoid.dynamicStringsSample.fresco.uri.CloudUri
import com.facebook.cache.common.CacheKey
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory
import com.facebook.imagepipeline.request.ImageRequest
import ru.mail.cloud.library.cache.fresco.CacheListChoice

class CloudCacheKeyFactory : DefaultCacheKeyFactory() {
    override fun getCacheKeySourceUri(sourceUri: Uri): Uri =
        CloudUri.removeQueryParameter(
            sourceUri.buildUpon().build(),
            ThumbProcessor.PARAM_GALLERY_METALEVEL_NAME,
            ThumbProcessor.PARAM_REQUEST_TIME
        )

    override fun getEncodedCacheKey(
        request: ImageRequest,
        sourceUri: Uri,
        callerContext: Any?
    ): CacheKey {
        val param = sourceUri.getQueryParameter(ThumbProcessor.PARAM_GALLERY_METALEVEL_NAME)
        val uri = CloudUri.removeQueryParameter(
            sourceUri.buildUpon().build(),
            ThumbProcessor.PARAM_GALLERY_METALEVEL_NAME,
            ThumbProcessor.PARAM_REQUEST_TIME,
            ThumbProcessor.PARAM_REQUEST_EXTENSION
        )

        return StoreCacheKey(
            getCacheKeySourceUri(uri).toString(),
            CacheListChoice.find(param, CacheListChoice.DAYS)
        )
    }
}
