package com.churickoid.dynamicStringsSample.domain.entity.json

import kotlinx.serialization.Serializable

@Serializable
abstract class Replacement {
    abstract val locale: String
}

@Serializable
data class StringReplacement(
    override val locale: String,
    val value: String
) : Replacement()


@Serializable
data class PluralsReplacement(
    override val locale: String,
    val items: List<PluralsItem>
) : Replacement()

@Serializable
data class PluralsItem(
    val quantity: String,
    val value: String
)
