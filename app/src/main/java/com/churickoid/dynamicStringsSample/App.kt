package com.churickoid.dynamicStringsSample

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.churickoid.dynamicStringsSample.domain.initialazer.ResourcesJsonInitializer
import com.churickoid.dynamicStringsSample.domain.resources.CustomResources
import com.churickoid.dynamicStringsSample.remote.FakeRemoteParams
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var customResources: CustomResources

    private lateinit var resources: Resources

    @Inject
    lateinit var resourcesJsonInitializer: ResourcesJsonInitializer

    override fun onCreate() {
        super.onCreate()
        resources = customResources
        if (FakeRemoteParams.isDynamicStringEnabled()) {
            resourcesJsonInitializer.initResources(
                FakeRemoteParams.getDynamicResourcesJson(),
                resources,
                packageName
            )
        }
    }


    override fun getResources(): Resources {
        return resources
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        resources = super.getResources()
    }
}