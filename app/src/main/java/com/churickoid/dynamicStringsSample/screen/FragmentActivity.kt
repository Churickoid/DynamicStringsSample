package com.churickoid.dynamicStringsSample.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.churickoid.dynamicStringsSample.BaseActivity
import com.churickoid.dynamicStringsSample.R
import com.churickoid.dynamicStringsSample.screen.fragment.BlankFragment

class FragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, BlankFragment())
                .commit()
        }
    }
}