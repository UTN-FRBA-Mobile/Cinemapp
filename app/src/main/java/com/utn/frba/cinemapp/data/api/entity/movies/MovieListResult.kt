package com.utn.frba.cinemapp.data.api.entity.movies

import com.google.gson.annotations.SerializedName

class MovieListResult {

    var page: Int = 1

    @SerializedName("results")
    lateinit var movies: List<MovieData>

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0
}