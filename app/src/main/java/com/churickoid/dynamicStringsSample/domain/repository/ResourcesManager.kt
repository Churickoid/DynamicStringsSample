package com.churickoid.dynamicStringsSample.domain.repository

import com.churickoid.dynamicStringsSample.remote.FakeRemoteParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ResourcesManager(
    val remoteParamsProvider: Flow<FakeRemoteParams>,
    val repository: ResourcesRepository,
    val coroutineScope: CoroutineScope = GlobalScope
) : ResourcesRepository by repository {
    init{
        remoteParamsProvider.filter { it.isDynamicStringEnabled() }.map {
            //очистка репозитория
            it.getDynamicResourcesJson()
        }.distinctUntilChanged().onEach {
            //парсим
            //чистим репозиторий либо поиск измененных и удаленных элементов
            //изменяем значения репозитория
        }
    }

    fun onConfigurationChanged(){
        // менять локаль
    }


}