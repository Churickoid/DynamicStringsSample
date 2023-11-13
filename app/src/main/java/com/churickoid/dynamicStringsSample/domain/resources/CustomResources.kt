package com.churickoid.dynamicStringsSample.domain.resources

import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics

class CustomResources(
    resources: Resources,
    private val stringResourcesReplacer: StringResourcesReplacer
) :
    Resources(
        resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration()
    ) {

    override fun getString(id: Int): String {
        return stringResourcesReplacer.getString(id) ?: super.getString(id)
    }

    override fun getString(id: Int, vararg formatArgs: Any?): String {
        return stringResourcesReplacer.getString(id, formatArgs) ?: super.getString(id, *formatArgs)
    }

    override fun getText(id: Int): CharSequence {
        return stringResourcesReplacer.getText(id) ?: super.getText(id)
    }

    override fun getText(id: Int, def: CharSequence?): CharSequence? {
        return stringResourcesReplacer.getText(id) ?: super.getText(id, def)
    }


}