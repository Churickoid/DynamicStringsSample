package ru.mail.cloud.resources.cache.fresco

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.atomic.AtomicReference

class FrescoDrawable(
    context : Context,
    scope: CoroutineScope,
    loader: DynamicDrawableLoader,
    url: String,
    private val defaultDrawableProvider: () -> Drawable,
) : Drawable() {

    private val _remoteBitmapAtomic = AtomicReference<Bitmap>()
    private val remoteBitmap: Bitmap?
        get() = _remoteBitmapAtomic.get()

    init {
        scope.launch {
            try {
                _remoteBitmapAtomic.set(loader.getImageByUrl(url, context) { invalidateSelf() })
            }
            catch (e: IOException){
                _remoteBitmapAtomic.set(null)
            }
        }
    }

    override fun draw(canvas: Canvas) {
        if (remoteBitmap != null) {
            val desiredWidth = bounds.width().toFloat()
            val desiredHeight = bounds.height().toFloat()

            val bitmapWidth = remoteBitmap!!.width.toFloat()
            val bitmapHeight = remoteBitmap!!.height.toFloat()

            val scaleX = desiredWidth / bitmapWidth
            val scaleY = desiredHeight / bitmapHeight

            val scale = minOf(scaleX, scaleY)

            val scaledWidth = bitmapWidth * scale
            val scaledHeight = bitmapHeight * scale

            val dx = (desiredWidth - scaledWidth) / 2
            val dy = (desiredHeight - scaledHeight) / 2

            val matrix = Matrix()
            matrix.setScale(scale, scale)
            matrix.postTranslate(dx, dy)

            canvas.drawBitmap(remoteBitmap!!, matrix, Paint())
            //remoteBitmap?.recycle()
        } else {
            val defaultDrawable =defaultDrawableProvider.invoke()
            // Получаем естественные размеры defaultDrawable
            val originalWidth = defaultDrawable.intrinsicWidth
            val originalHeight = defaultDrawable.intrinsicHeight

            // Рассчитываем максимальные размеры, которые могут вместиться в bounds
            val maxWidth = bounds.width().toFloat()
            val maxHeight = bounds.height().toFloat()

            // Рассчитываем масштаб по обеим осям
            val scaleX = maxWidth / originalWidth
            val scaleY = maxHeight / originalHeight

            // Выбираем минимальный масштаб, чтобы изображение влезло полностью в bounds
            val scale = minOf(scaleX, scaleY)

            // Рассчитываем новые размеры с учетом масштаба
            val newWidth = (originalWidth * scale).toInt()
            val newHeight = (originalHeight * scale).toInt()

            // Рассчитываем смещение для центрирования
            val dx = (bounds.width() - newWidth) / 2
            val dy = (bounds.height() - newHeight) / 2

            // Устанавливаем границы с учетом новых размеров
            defaultDrawable.setBounds(dx, dy, dx + newWidth, dy + newHeight)

            // Рисуем defaultDrawable
            defaultDrawable.draw(canvas)
        }
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    companion object {
        private const val TAG = "FrescoDrawable"
    }
}