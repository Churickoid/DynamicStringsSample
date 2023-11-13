package com.churickoid.dynamicStringsSample.domain.entity


data class ResourceKey(
    val type: ResourceType,
    val name: String
)

enum class ResourceType(type:String){
    STRING("string"), PLURALS("plurals"), //DRAWABLE("drawable")
}

interface ResourceValue

data class ResourceString(val value: String): ResourceValue

data class ResourcePlurals(val values: Map<String,String>): ResourceValue
