package ru.mail.cloud.resources.di

import android.content.Context
import com.churickoid.dynamicStringsSample.di.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import ru.mail.cloud.resources.analytics.ResourcesAnalytics
import ru.mail.cloud.resources.analytics.ResourcesAnalyticsImpl
import ru.mail.cloud.resources.cache.ResourcesMemcache
import ru.mail.cloud.resources.cache.ResourcesMemcacheImpl
import ru.mail.cloud.resources.cache.UpdateResourcesMemcache
import ru.mail.cloud.resources.cache.fresco.DynamicDrawableLoader
import ru.mail.cloud.resources.cache.initializer.JsonResourcesParser
import ru.mail.cloud.resources.replacer.ResourcesReplacer
import ru.mail.cloud.resources.replacer.ResourcesReplacerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ResourcesModule {

    @Singleton
    @Provides
    fun provideResourcesMemcacheImpl(
        dynamicDrawableLoader: DynamicDrawableLoader,
        @ApplicationContext
        appContext : Context,
        @ApplicationScope
        scope: CoroutineScope
    ) =
        ResourcesMemcacheImpl(appContext, dynamicDrawableLoader, scope)

    @Singleton
    @Provides
    fun provideResourcesMemcache(impl: ResourcesMemcacheImpl): ResourcesMemcache = impl

    @Singleton
    @Provides
    fun providerUpdateResourcesMemcache(impl: ResourcesMemcacheImpl): UpdateResourcesMemcache = impl

    @Singleton
    @Provides
    fun provideResourcesReplacer(resourcesMemcache: ResourcesMemcache): ResourcesReplacer =
        ResourcesReplacerImpl(
             resourcesMemcache
        )

    @Singleton
    @Provides
    fun provideJsonResourcesParser(resourcesMemcache: UpdateResourcesMemcache): JsonResourcesParser =
        JsonResourcesParser(resourcesMemcache)

    @Singleton
    @Provides
    fun provideResourcesAnalytics(): ResourcesAnalytics = ResourcesAnalyticsImpl()
}


