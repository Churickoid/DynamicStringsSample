package com.churickoid.dynamicStringsSample.screen

import android.graphics.Path
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.churickoid.dynamicStringsSample.BaseActivity
import com.churickoid.dynamicStringsSample.R


class TestBitmapDrawableActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_bitmap_drawable)

        val image = findViewById<ImageView>(R.id.test_image)
        image.setImageResource(R.drawable.ic_launcher_background)
        val button1 = findViewById<Button>(R.id.button_center)
        button1.setOnClickListener {
            image.scaleType = ImageView.ScaleType.CENTER
        }
        val button2 = findViewById<Button>(R.id.button_center_crop)
        button2.setOnClickListener {
            image.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        val button3 = findViewById<Button>(R.id.button_center_inside)
        button3.setOnClickListener {
            image.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
        val button4 = findViewById<Button>(R.id.button_matrix)
        button4.setOnClickListener {
            image.scaleType = ImageView.ScaleType.MATRIX
        }
    }
}