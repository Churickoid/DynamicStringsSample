package com.churickoid.dynamicStringsSample.screen

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.churickoid.dynamicStringsSample.BaseActivity
import com.churickoid.dynamicStringsSample.R

class ImageViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imageview_params)

        val id = R.drawable.ic_launcher_background

        val image0 = findViewById<ImageView>(R.id.image_for_replace0)
        image0.setImageResource(id)
        val image1 = findViewById<ImageView>(R.id.image_for_replace1)
        image1.setImageResource(id)
        val image2 = findViewById<ImageView>(R.id.image_for_replace2)
        image2.setImageResource(id)
        val image3 = findViewById<ImageView>(R.id.image_for_replace3)
        image3.setImageResource(id)
    }
}