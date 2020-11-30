package com.utn.frba.cinemapp.domain.entities.movies


data class VideoEntity(
    var id: String,
    var name: String,
    var youtubeKey: String? = null
) {

    companion object {
        const val SOURCE_YOUTUBE = "YouTube"
        const val TYPE_TRAILER = "Trailer"
    }
}