package com.utn.frba.cinemapp.domain.servicies

import com.utn.frba.cinemapp.domain.entities.MovieEntity

interface MoviesDataStore {

    fun getMovieById(movieId: Int): MovieEntity?
    fun getMovies(): List<MovieEntity>
    fun search(query: String): List<MovieEntity>
}