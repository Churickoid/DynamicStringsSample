package com.churickoid.dynamicStringsSample.domain.resources

import com.churickoid.dynamicStringsSample.domain.repository.ResourcesRepository
import javax.inject.Inject


class StringResourcesReplacerImpl @Inject constructor(val resourcesRepository: ResourcesRepository) :
    StringResourcesReplacer {


    override fun getString(resId: Int): String? {
        return resourcesRepository.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String? {
        return resourcesRepository.getString(resId)
            ?.let { String.format(resourcesRepository.locale, it, *formatArgs) }
    }


    override fun getText(resId: Int): CharSequence? {
        return resourcesRepository.getString(resId)
    }


}