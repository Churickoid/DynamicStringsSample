package com.churickoid.dynamicStringsSample.fresco

import android.content.Context
import com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory
import com.facebook.imagepipeline.core.FileCacheFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import ru.mail.cloud.resources.cache.fresco.DynamicDrawableLoader
import ru.mail.cloud.resources.cache.fresco.DynamicDrawableLoaderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FrescoModule {
    @Provides
    @Singleton
    fun providesDefaultCacheKeyFactory(): DefaultCacheKeyFactory =
        CloudCacheKeyFactory()


    @Provides
    @Singleton
    fun providesFrescoHelperProvider(
        @ApplicationContext
        context: Context,
        cacheKeyFactory: DefaultCacheKeyFactory
    ): FrescoHelper = FrescoHelper(
            context,
            cacheKeyFactory,
        )


    @Provides
    @Singleton
    fun providesDynamicDrawableLoader(
        frescoHelperProvider: FrescoHelper,
    ): DynamicDrawableLoader =
        DynamicDrawableLoaderImpl(
            frescoHelperProvider
        )



}