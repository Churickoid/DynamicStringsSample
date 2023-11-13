package com.churickoid.dynamicStringsSample.domain.entity.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class JsonResources {
    abstract val type: String
    abstract val enabled: Boolean
    abstract val name: String
    abstract val replacements:  List<Replacement>
}

@Serializable
@SerialName("string")
data class StringJsonResources(
    override val type: String,
    override val enabled: Boolean,
    override val name: String,
    override val replacements: List<StringReplacement>
) : JsonResources()

@Serializable
@SerialName("plurals")
data class PluralsJsonResources(
    override val type: String,
    override val enabled: Boolean,
    override val name: String,
    override val replacements: List<PluralsReplacement>
) : JsonResources()









