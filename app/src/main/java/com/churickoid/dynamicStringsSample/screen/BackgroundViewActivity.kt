package com.churickoid.dynamicStringsSample.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.churickoid.dynamicStringsSample.BaseActivity
import com.churickoid.dynamicStringsSample.R

class BackgroundViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_view)

        val cardView = findViewById<CardView>(R.id.test_card)
        cardView.setBackgroundResource(R.drawable.first_klass)
        val textView = findViewById<TextView>(R.id.test_text)
        textView.setBackgroundResource(R.drawable.first_klass)
        val button = findViewById<Button>(R.id.test_button)
        button.setBackgroundResource(R.drawable.first_klass)
    }
}