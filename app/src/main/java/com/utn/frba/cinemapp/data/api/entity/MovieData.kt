package com.utn.frba.cinemapp.data.api.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieData(

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("vote_count")
    var voteCount: Int = 0,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("adult")
    var adult: Boolean = false,

    @SerializedName("popularity")
    var popularity: Double = 0.0,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("original_title")
    var originalTitle: String,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: Date,

    @SerializedName("overview")
    var overview: String? = null
)