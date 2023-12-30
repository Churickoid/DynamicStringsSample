package com.churickoid.dynamicStringsSample.screen

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.churickoid.dynamicStringsSample.BaseActivity
import com.churickoid.dynamicStringsSample.R

class SingleImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_image)
        val image = findViewById<ImageView>(R.id.test_image)
        image.setImageResource(R.drawable.ic_launcher_background)
    }
}