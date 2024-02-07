package com.churickoid.dynamicStringsSample.screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.churickoid.dynamicStringsSample.BaseActivity
import com.churickoid.dynamicStringsSample.R

class SingleImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_image)
        var switch = false
        val image = findViewById<ImageView>(R.id.test_image)
        val button = findViewById<Button>(R.id.button_visibility)
        image.setImageResource(R.drawable.ic_about_logo)
        button.setOnClickListener {
            if (switch){
                image.visibility = View.VISIBLE
            }
            else{
                image.visibility = View.GONE
            }
            switch = !switch
        }
    }
}