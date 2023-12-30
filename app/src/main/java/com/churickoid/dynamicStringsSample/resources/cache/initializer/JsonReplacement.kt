package ru.mail.cloud.resources.cache.initializer

import kotlinx.serialization.Serializable

@Serializable
abstract class JsonReplacement {
    abstract val locale: String
}

@Serializable
data class StringJsonReplacement(
    override val locale: String,
    val value: String,
) : JsonReplacement()

@Serializable
data class PluralsJsonReplacement(
    override val locale: String,
    val items: List<PluralsItem>
) : JsonReplacement()

@Serializable
data class PluralsItem(
    val quantity: String,
    val value: String
)
@Serializable
data class DrawableJsonReplacement(
    val density: String,
    val url: String
)
