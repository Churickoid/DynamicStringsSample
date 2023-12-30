package ru.mail.cloud.library.cache.fresco

import android.text.TextUtils

@Suppress("MagicNumber")
enum class CacheListChoice(val percentCacheSize: Float) {
    DAYS(.85f),
    MONTH(.10f),
    YEAR(.05f);

    companion object {
        @JvmStatic
        fun find(name: String?, defaultType: CacheListChoice): CacheListChoice {
            if (TextUtils.isEmpty(name)) {
                return defaultType
            }
            for (chooser in values()) {
                if (chooser.name.equals(name, ignoreCase = true)) {
                    return chooser
                }
            }
            return defaultType
        }
    }
}
