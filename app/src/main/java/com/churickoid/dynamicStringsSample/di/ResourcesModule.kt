package com.churickoid.dynamicStringsSample.di

import android.app.Application
import com.churickoid.dynamicStringsSample.domain.initialazer.ResourcesJsonMapper
import com.churickoid.dynamicStringsSample.domain.repository.ResourcesRepository
import com.churickoid.dynamicStringsSample.domain.repository.ResourcesRepositoryMapImpl
import com.churickoid.dynamicStringsSample.domain.resources.CustomResources
import com.churickoid.dynamicStringsSample.domain.resources.StringResourcesReplacer
import com.churickoid.dynamicStringsSample.domain.resources.StringResourcesReplacerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {

    @Singleton
    @Binds
    abstract fun bindStringResourcesReplacer(impl: StringResourcesReplacerImpl): StringResourcesReplacer

    @Singleton
    @Binds
    abstract fun bindResourcesRepository(impl: ResourcesRepositoryMapImpl): ResourcesRepository

    companion object {
        @Singleton
        @Provides
        fun provideCustomResources(
            app: Application,
            stringResourcesReplacer: StringResourcesReplacer
        ): CustomResources {
            return CustomResources(app.resources, stringResourcesReplacer)
        }

        @Singleton
        @Provides
        fun provideResourcesJsonMapper(
        ): ResourcesJsonMapper {
            return ResourcesJsonMapper()
        }
    }
}