package com.utn.frba.cinemapp.data.api.service

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.config.URL_BACKEND
import com.utn.frba.cinemapp.data.api.entity.MovieListResult
import com.utn.frba.cinemapp.data.api.mappers.MovieDataEntityMapper
import com.utn.frba.cinemapp.data.api.rest.ThemoviedbRestApi
import com.utn.frba.cinemapp.domain.entities.GenreEntity
import com.utn.frba.cinemapp.domain.entities.MovieDetailsEntity
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesDataStoreImpl : MoviesDataStore {

    private val movieDataMapper = MovieDataEntityMapper()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BACKEND)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiRest = retrofit.create<ThemoviedbRestApi>(ThemoviedbRestApi::class.java)

    override fun getPopularMoviesAsync(
        onSuccess: (List<MovieEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {

        onSuccess(mocksMovies())

//        apiRest.getPopularMovies().enqueue(object : Callback<MovieListResult> {
//
//            override fun onFailure(call: Call<MovieListResult>, t: Throwable) {
//                onError(t)
//            }
//
//            override fun onResponse(
//                call: Call<MovieListResult>,
//                response: Response<MovieListResult>
//            ) {
//                val r = response.body() as MovieListResult
//                onSuccess(r.movies.map { movieDataMapper.mapFrom(it) })
//            }
//        })
    }


    private fun mocksMovies(): List<MovieEntity> {

        val genres = mutableListOf(GenreEntity(1, "Drama"), GenreEntity(1, "Suspenso"))

        return listOf(
            MovieEntity(
                title = "asd",
                posterPath = "asd",
                originalLanguage = "asd",
                originalTitle = "asd",
                backdropPath = "asd",
                releaseDate = "asd",
                overview = "asd",
                details = MovieDetailsEntity(
                    genres = genres
                ),
                popularity = 3.3
            ),
            MovieEntity(
                title = "qwe",
                posterPath = "qwe",
                originalLanguage = "qw",
                originalTitle = "qwe",
                backdropPath = "qwe",
                releaseDate = "qwe",
                overview = "qwe",
                details = MovieDetailsEntity(
                    genres = genres
                ),
                popularity = 4.5
            ),
            MovieEntity(
                title = "asd",
                posterPath = "asd",
                originalLanguage = "asd",
                originalTitle = "asd",
                backdropPath = "asd",
                releaseDate = "asd",
                overview = "asd",
                details = MovieDetailsEntity(
                    genres = genres
                ),
                popularity = 4.1
            ),
            MovieEntity(
                title = "qwe",
                posterPath = "qwe",
                originalLanguage = "qw",
                originalTitle = "qwe",
                backdropPath = "qwe",
                releaseDate = "qwe",
                overview = "qwe",
                details = MovieDetailsEntity(
                    genres = genres
                ),
                popularity = 4.5
            ),
            MovieEntity(
                title = "asd",
                posterPath = "asd",
                originalLanguage = "asd",
                originalTitle = "asd",
                backdropPath = "asd",
                releaseDate = "asd",
                overview = "asd",
                details = MovieDetailsEntity(
                    genres = genres
                ),
                popularity = 2.7
            ),
            MovieEntity(
                title = "qwe",
                posterPath = "qwe",
                originalLanguage = "qw",
                originalTitle = "qwe",
                backdropPath = "qwe",
                releaseDate = "qwe",
                overview = "qwe",
                details = MovieDetailsEntity(
                    genres = genres
                ),
                popularity = 1.8
            )
        ).sortedByDescending { it.popularity }
    }
}
