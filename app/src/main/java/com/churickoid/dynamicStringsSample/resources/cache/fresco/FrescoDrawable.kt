package ru.mail.cloud.resources.cache.fresco

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.atomic.AtomicReference

class FrescoDrawable(
    private val context: Context,
    scope: CoroutineScope,
    loader: DynamicDrawableLoader,
    url: String,
    defaultBitmap: Bitmap,
) : BitmapDrawable(context.resources, defaultBitmap) {

    private val _remoteBitmapAtomic = AtomicReference<Bitmap>()
    private val remoteBitmap: Bitmap?
        get() = _remoteBitmapAtomic.get()

    private var mRectAndInsetsDirty = true
    private val mRect = Rect()

    init {
        scope.launch {
            try {
                _remoteBitmapAtomic.set(loader.getImageByUrl(url, context))
                invalidateSelf()
            } catch (e: IOException) {
                _remoteBitmapAtomic.set(null)
            }
        }
    }

    override fun draw(canvas: Canvas) {
        if (bitmap.isRecycled) {
            return
        }
        updateRectAndInsetsIfDirty()
        if (remoteBitmap != null) {
            canvas.drawBitmap(remoteBitmap!!, null, mRect, paint)
        } else {
            canvas.drawBitmap(bitmap, null, mRect, paint)
        }

    }

    private fun updateRectAndInsetsIfDirty() {
        if (mRectAndInsetsDirty) {
            val bounds = bounds
            if (bitmap.width > 0 && bitmap.height > 0 && bounds.width() > 0 && bounds.height() > 0) {
                Gravity.apply(
                    Gravity.FILL,
                    bitmap.width,
                    bitmap.height,
                    bounds,
                    mRect,
                    layoutDirection
                )
            }
            mRectAndInsetsDirty = false
            invalidateSelf()
        }
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mRectAndInsetsDirty = true
    }


    companion object {
        private const val TAG = "FrescoDrawable"
    }
}