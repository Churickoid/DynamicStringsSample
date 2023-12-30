package ru.mail.cloud.resources.cache.initializer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class JsonResourceType {
    @SerialName("string")
    STRING,
    @SerialName("plurals")
    PLURALS,
}