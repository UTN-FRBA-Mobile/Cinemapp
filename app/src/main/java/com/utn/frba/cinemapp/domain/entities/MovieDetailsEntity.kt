package com.utn.frba.cinemapp.domain.entities

import java.io.Serializable

data class MovieDetailsEntity(
    var belongsToCollection: Any? = null,
    var budget: Int? = null,
    var homepage: String? = null,
    var imdbId: String? = null,
    var overview: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var status: String? = null,
    var tagline: String? = null,
    var reviews: List<ReviewEntity>? = null,
    var videos: List<VideoEntity>? = null,
    var genres: List<GenreEntity>? = null
) : Serializable