package com.churickoid.dynamicStringsSample

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun getResources(): Resources? {
        return applicationContext.resources
    }
}