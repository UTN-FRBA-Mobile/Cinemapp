package com.utn.frba.cinemapp.data.api.rest

import com.utn.frba.cinemapp.data.api.entity.MovieDetailsData
import com.utn.frba.cinemapp.data.api.entity.MovieListResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerMovieRestApi {

    @GET("api/v1/movies/popular")
    fun getPopularMovies(): Call<MovieListResult>

    @GET("api/v1/movies/{id}")
    fun getDetailMovie(@Path(value = "id") id: Int): Call<MovieDetailsData>
}