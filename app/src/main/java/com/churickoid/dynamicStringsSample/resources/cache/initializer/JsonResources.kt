package ru.mail.cloud.resources.cache.initializer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class JsonResources {
    abstract val enabled: Boolean
    abstract val name: String
    abstract val replacements: List<JsonReplacement>
}

@Serializable
@SerialName("string")
data class StringJsonResources(
    override val enabled: Boolean,
    override val name: String,
    override val replacements: List<StringJsonReplacement>,
) : JsonResources()

@Serializable
@SerialName("plurals")
data class PluralsJsonResources(
    override val enabled: Boolean,
    override val name: String,
    override val replacements: List<PluralsJsonReplacement>,
) : JsonResources()


@Serializable
data class DrawableJsonResources(
    val enabled: Boolean,
    val subtype: String,
    val name: String,
    val replacements: List<DrawableJsonReplacement>,
)