package com.utn.frba.cinemapp.domain.entities.movies

import java.io.Serializable
import java.util.*


data class MovieEntity(

    var id: Int = 0,
    var voteCount: Int = 0,
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var popularity: Double = 0.0,
    var adult: Boolean = false,
    var details: MovieDetailsEntity? = null,
    var title: String,
    var posterPath: String?,
    var originalLanguage: String,
    var originalTitle: String,
    var backdropPath: String?,
    var releaseDate: Date,
    var overview: String? = null,
    var genreIds: List<Int>? = null

) : Serializable
