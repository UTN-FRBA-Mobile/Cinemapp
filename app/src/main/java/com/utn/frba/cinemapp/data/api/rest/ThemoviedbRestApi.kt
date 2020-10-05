package com.utn.frba.cinemapp.data.api.rest

import com.utn.frba.cinemapp.data.api.entity.MovieListResult
import retrofit2.Call
import retrofit2.http.GET

interface ThemoviedbRestApi {

    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieListResult>
}