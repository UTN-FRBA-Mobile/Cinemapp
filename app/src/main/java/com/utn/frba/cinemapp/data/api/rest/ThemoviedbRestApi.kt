package com.utn.frba.cinemapp.data.api.rest

import com.utn.frba.cinemapp.data.api.entity.DetailsData
import com.utn.frba.cinemapp.data.api.entity.MovieListResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ThemoviedbRestApi {

    @GET("movie/{id}?append_to_response=videos,reviews")
    fun getMovieDetails(@Path("id") movieId: Int): DetailsData

    @GET("movie/popular")
    fun getPopularMovies(): MovieListResult

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): MovieListResult
}