package com.utn.frba.cinemapp.data.api.rest

import com.utn.frba.cinemapp.data.api.entity.GenresResult
import com.utn.frba.cinemapp.data.api.entity.MovieDetailsData
import com.utn.frba.cinemapp.data.api.entity.MovieListResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ThemoviedbRestApi {

    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieListResult>

    @GET("movie/{id}?append_to_response=videos,reviews")
    fun getDetailMovie(@Path(value = "id") id: Int): Call<MovieDetailsData>

    @GET("genre/movie/list")
    fun getGenres(): Call<GenresResult>
}