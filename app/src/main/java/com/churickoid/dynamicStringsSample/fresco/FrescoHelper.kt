package com.churickoid.dynamicStringsSample.fresco

import android.content.Context
import android.net.Uri
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.util.ByteConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder
import com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory
import com.facebook.imagepipeline.core.FileCacheFactory
import com.facebook.imagepipeline.core.ImagePipeline
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImagePipelineFactory
import com.facebook.imagepipeline.request.ImageRequestBuilder

class FrescoHelper(
    context: Context,
    val keyFactory: DefaultCacheKeyFactory,
) {
    val imagePipelineFactory: ImagePipelineFactory
        get() = Fresco.getImagePipelineFactory()

    val imagePipeline: ImagePipeline
        get() = Fresco.getImagePipeline()

    init {
        val diskCacheConfig = DiskCacheConfig.newBuilder(context)
            .setMaxCacheSize(MAX_CACHE_SIZE)
            .setMaxCacheSizeOnLowDiskSpace(MAX_CACHE_SIZE_ON_LOW_SPACE)
            .build()
        val config = ImagePipelineConfig
            .newBuilder(context)
            .setMainDiskCacheConfig(diskCacheConfig)
            .setCacheKeyFactory(keyFactory)
            .build()
        Fresco.initialize(context, config)
    }

    fun newDraweeControllerBuilder(): PipelineDraweeControllerBuilder =
        Fresco.newDraweeControllerBuilder()

    fun newImageRequestBuilderWithSource(uri: Uri): ImageRequestBuilder =
        ImageRequestBuilder.newBuilderWithSource(uri)


    companion object {

        private const val MAX_CACHE_SIZE = 500L * ByteConstants.MB
        private const val MAX_CACHE_SIZE_ON_LOW_SPACE = 50L * ByteConstants.MB
    }
}
