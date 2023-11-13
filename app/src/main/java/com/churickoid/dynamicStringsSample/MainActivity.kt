package com.churickoid.dynamicStringsSample

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.test_str)
        text.setText(R.string.test_string)
    }

    override fun getResources(): Resources {
        return application.resources
    }
}