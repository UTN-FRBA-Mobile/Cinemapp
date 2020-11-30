package com.utn.frba.cinemapp.data.api.service

import android.util.Log
import com.utn.frba.cinemapp.config.URL_BACKEND
import com.utn.frba.cinemapp.config.URL_PROXY_MOVIES
import com.utn.frba.cinemapp.data.api.entity.movies.GenresResult
import com.utn.frba.cinemapp.data.api.entity.movies.MovieDetailsData
import com.utn.frba.cinemapp.data.api.entity.movies.MovieListResult
import com.utn.frba.cinemapp.data.api.mappers.DetailsMovieDataEntityMapper
import com.utn.frba.cinemapp.data.api.mappers.MovieDataEntityMapper
import com.utn.frba.cinemapp.data.api.rest.ServerMovieRestApi
import com.utn.frba.cinemapp.data.api.rest.ThemoviedbRestApi
import com.utn.frba.cinemapp.domain.entities.movies.GenreEntity
import com.utn.frba.cinemapp.domain.entities.movies.MovieDetailsEntity
import com.utn.frba.cinemapp.domain.entities.movies.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MoviesDataStoreImpl : MoviesDataStore {

    private val movieDataMapper = MovieDataEntityMapper()
    private val detailDataMapper = DetailsMovieDataEntityMapper()

    private val restTheMovieDb = Retrofit.Builder()
        .baseUrl(URL_PROXY_MOVIES)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<ThemoviedbRestApi>(ThemoviedbRestApi::class.java)

    private val restServer = Retrofit.Builder()
        .baseUrl(URL_BACKEND)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<ServerMovieRestApi>(ServerMovieRestApi::class.java)


    override fun getPopularMoviesAsync(
        onSuccess: (List<MovieEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {

        restServer.getPopularMovies().enqueue(object : Callback<MovieListResult> {

            override fun onFailure(call: Call<MovieListResult>, t: Throwable) {
                onError(t)
                onSuccess(mocksMovies())
            }

            override fun onResponse(
                call: Call<MovieListResult>,
                response: Response<MovieListResult>
            ) {
                Log.v("RESPUESTA", response.body().toString())
                val r = response.body() as MovieListResult
                onSuccess(r.movies.map { movieDataMapper.mapFrom(it) })
            }
        })
    }

    override fun getDetailMovieAsync(
        id: Int,
        onSuccess: (MovieEntity) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        restServer.getDetailMovie(id).enqueue(object : Callback<MovieDetailsData> {

            override fun onResponse(
                call: Call<MovieDetailsData>,
                response: Response<MovieDetailsData>
            ) {
                Log.v("RESPUESTA", response.body().toString())
                val r = response.body() as MovieDetailsData
                onSuccess(detailDataMapper.mapFrom(r))
            }

            override fun onFailure(call: Call<MovieDetailsData>, t: Throwable) {
                onError(t)
            }
        })
    }

    override fun getGenresAsync(
        onSuccess: (List<GenreEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        restTheMovieDb.getGenres().enqueue(object : Callback<GenresResult> {

            override fun onResponse(
                call: Call<GenresResult>,
                response: Response<GenresResult>
            ) {
                Log.v("RESPUESTA", response.body().toString())
                val r = response.body() as GenresResult
                onSuccess(r.genres.map { GenreEntity(it.id, it.name) })
            }

            override fun onFailure(call: Call<GenresResult>, t: Throwable) {
                onError(t)
            }
        })
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
                releaseDate = Date(),
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
                releaseDate = Date(),
                overview = "qwe",
                details = MovieDetailsEntity(
                    genres = genres
                ),
                popularity = 4.5
            )
        ).sortedByDescending { it.popularity }
    }
}
