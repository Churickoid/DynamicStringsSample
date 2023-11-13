package com.churickoid.dynamicStringsSample.domain.initialazer


import com.churickoid.dynamicStringsSample.domain.entity.json.JsonResources
import com.churickoid.dynamicStringsSample.domain.entity.json.PluralsJsonResources
import com.churickoid.dynamicStringsSample.domain.entity.json.StringJsonResources
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.util.Locale

class ResourcesJsonMapper {

    private val jsonConfig = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }


    fun jsonToDataList(jsonString: String): List<JsonResources> {
        return jsonConfig.decodeFromString(jsonString)
    }



}


