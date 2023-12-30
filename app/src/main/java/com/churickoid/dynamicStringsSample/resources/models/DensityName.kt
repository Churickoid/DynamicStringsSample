package ru.mail.cloud.resources.models

@JvmInline
value class DensityName(val name: String) {
    companion object {
        private val DENSITIES = mapOf(
            0.75f to "ldpi",
            1.0f to "mdpi",
            1.5f to "hdpi",
            2.0f to "xhdpi",
            3.0f to "xxhdpi",
            4.0f to "xxxhdpi"
        )

        fun getDensityNameByValue(value: Float): DensityName {
            return DensityName(
                DENSITIES.entries.lastOrNull { (bound, _) -> value >= bound }?.value ?: "ldpi"
            )
        }
    }
}