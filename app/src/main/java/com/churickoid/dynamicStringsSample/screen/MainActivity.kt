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
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, SingleImageActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, RecyclerActivity::class.java)
            startActivity(intent)
        }

    }
}