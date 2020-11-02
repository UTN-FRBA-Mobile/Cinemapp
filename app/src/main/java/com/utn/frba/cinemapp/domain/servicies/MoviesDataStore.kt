package com.utn.frba.cinemapp.domain.servicies

import com.utn.frba.cinemapp.domain.entities.MovieEntity

interface MoviesDataStore {

    /**
     *
     */
    fun getPopularMoviesAsync(
        onSuccess: (List<MovieEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    )

    /**
     *
     */
    fun getDetailMovieAsync(
        id: Int,
        onSuccess: (MovieEntity) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}