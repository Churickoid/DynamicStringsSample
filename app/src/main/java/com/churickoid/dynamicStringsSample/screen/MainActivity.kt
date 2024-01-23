package com.churickoid.dynamicStringsSample.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.churickoid.dynamicStringsSample.BaseActivity
import com.churickoid.dynamicStringsSample.R


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button10 = findViewById<Button>(R.id.button10)
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, SingleImageActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerActivity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, ScaleTypeActivity::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageViewActivity::class.java)
            startActivity(intent)
        }

        button5.setOnClickListener {
            val intent = Intent(this@MainActivity, ComposeActivity::class.java)
            startActivity(intent)
        }

        button10.setOnClickListener {
            val intent = Intent(this@MainActivity, TestBitmapDrawableActivity::class.java)
            startActivity(intent)
        }

    }
}