package com.churickoid.dynamicStringsSample.domain.initialazer

import android.content.res.Resources
import com.churickoid.dynamicStringsSample.domain.entity.ResourceKey
import com.churickoid.dynamicStringsSample.domain.repository.ResourcesRepository
import javax.inject.Inject

class ResourcesJsonInitializer @Inject constructor(
    private val resourcesRepository: ResourcesRepository,
    private val jsonMapper: ResourcesJsonMapper
) {

    fun initResources(json: String, resources: Resources, packageName: String) {
        val locale = resources.configuration.locales[0]
        resourcesRepository.enableFeature(locale)

        val jsonDataList = jsonMapper.jsonToDataList(json)

        jsonDataList.forEach {
            if (it.enabled){

            }
        }

    }

    private fun getResourceIdByKey(
        key: ResourceKey,
        resources: Resources,
        packageName: String
    ): Int {
        return resources.getIdentifier(key.name, key.type.name, packageName)
    }


}