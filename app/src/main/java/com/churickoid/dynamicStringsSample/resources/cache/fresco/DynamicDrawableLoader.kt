package ru.mail.cloud.resources.cache.fresco

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.churickoid.dynamicStringsSample.fresco.FrescoHelper
import com.facebook.common.executors.UiThreadImmediateExecutorService
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableBitmap
import com.facebook.imagepipeline.image.CloseableImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DynamicDrawableLoader(
    val frescoHelper: FrescoHelper,
) {

    suspend fun getImageByUrl(url: String, context: Context, update: () -> Unit): Bitmap =
        withContext(Dispatchers.IO) {
            val imageRequest = frescoHelper
                .newImageRequestBuilderWithSource(Uri.parse(url))
                .disableDiskCache()
                .build()

            val dataSource = frescoHelper.imagePipeline
                .fetchDecodedImage(imageRequest, context)

            if (dataSource.isFinished) {
                (dataSource.result?.get() as CloseableBitmap).underlyingBitmap.also { update() }
            } else {
                suspendCoroutine { continuation ->
                    dataSource.subscribe(
                        object : BaseBitmapDataSubscriber() {

                            override fun onNewResultImpl(bitmap: Bitmap?) {
                                continuation.resume(bitmap!!.copy(bitmap.config, false))
                                Log.e("FrescoDrawable", "uploaded")
                                update()
                            }

                            override fun onFailureImpl(dataSource: DataSource<CloseableReference<CloseableImage?>>) {
                                continuation.resumeWithException(IOException("failed to load thumb url: $url"))
                                Log.e("FrescoDrawable", "fail")
                                update()
                            }

                            override fun onCancellation(dataSource: DataSource<CloseableReference<CloseableImage?>>) {
                                continuation.resumeWithException(IOException("load thumb cancelled url: $url"))
                                Log.e("FrescoDrawable", "cancel")
                                update()
                            }
                        },
                        UiThreadImmediateExecutorService.getInstance()
                    )
                }
            }
        }
}
