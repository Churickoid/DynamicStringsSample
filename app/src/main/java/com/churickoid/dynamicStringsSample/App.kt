package com.churickoid.dynamicStringsSample

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.churickoid.dynamicStringsSample.di.ApplicationScope
import dagger.hilt.InstallIn
import dagger.hilt.android.EarlyEntryPoint
import dagger.hilt.android.EarlyEntryPoints
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import ru.mail.cloud.resources.CloudResourceWrapper
import ru.mail.cloud.resources.analytics.ResourcesAnalytics
import ru.mail.cloud.resources.cache.initializer.JsonResourcesParser
import ru.mail.cloud.resources.replacer.ResourcesReplacer

@HiltAndroidApp
class App : Application() {


    private lateinit var res: Resources

    @InstallIn(SingletonComponent::class)
    @EarlyEntryPoint
    interface ApplicationEarlyEntryPoint {
        fun resourceReplacer(): ResourcesReplacer

        fun jsonResourcesParser(): JsonResourcesParser

        fun resourcesAnalytics(): ResourcesAnalytics

        @ApplicationScope
        fun applicationCoroutineScope(): CoroutineScope
    }

    override fun onCreate() {
        super.onCreate()

        val appEntryPoint = EarlyEntryPoints.get(
            applicationContext,
            ApplicationEarlyEntryPoint::class.java
        )

        res = CloudResourceWrapper(
            super.getResources(),
            appEntryPoint.resourceReplacer(),
            appEntryPoint.resourcesAnalytics(),
            appEntryPoint.jsonResourcesParser(),
            appEntryPoint.applicationCoroutineScope()
        )
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        res = super.getResources()
    }

    override fun getResources(): Resources {
        return res
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val resources = super.getResources()
        res.updateConfiguration(resources.configuration, resources.displayMetrics)
    }

}