package ru.mail.cloud.resources.analytics

interface ResourcesAnalytics {
    fun onStringReplaced(id: Int, defaultString: String, newString: String) {
    }
    fun onQuantityStringReplaced(id: Int, quantity: Int, defaultString: String, newString: String) {
    }
}
