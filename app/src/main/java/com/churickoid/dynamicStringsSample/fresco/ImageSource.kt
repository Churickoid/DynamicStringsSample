package ru.mail.cloud.library.cache.fresco

sealed interface ImageSource {
    class NodeId(val nodeId: String) : ImageSource

    class Sha1(val sha1: ByteArray, val isVideo: Boolean = false) : ImageSource

    class Url(val url: String) : ImageSource

    class PathToFile(val path: String, val isVideo: Boolean = false) : ImageSource

    class Avatar(
        val id: String, // в id закодирован квадрат координат с лицом или объектом
        val nodeId: String
    ) : ImageSource
}
