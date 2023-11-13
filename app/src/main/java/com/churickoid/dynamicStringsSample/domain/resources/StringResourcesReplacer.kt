package com.churickoid.dynamicStringsSample.domain.resources

interface StringResourcesReplacer {
    fun getString(resId: Int): String?
    fun getString(resId: Int, vararg formatArgs: Any): String?
    fun getText(resId: Int): CharSequence?
}