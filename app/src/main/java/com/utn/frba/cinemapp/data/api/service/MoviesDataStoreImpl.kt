package com.utn.frba.cinemapp.data.api.service

import com.utn.frba.cinemapp.data.api.mappers.DetailsDataMovieEntityMapper
import com.utn.frba.cinemapp.data.api.mappers.MovieDataEntityMapper
import com.utn.frba.cinemapp.data.api.rest.ThemoviedbRestApi
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore

class MoviesDataStoreImpl(private val api: ThemoviedbRestApi) : MoviesDataStore {

    private val movieDataMapper = MovieDataEntityMapper()
    private val detailedDataMapper = DetailsDataMovieEntityMapper()

    override fun search(query: String): List<MovieEntity> {
        return api.searchMovies(query).movies.map { movieDataMapper.mapFrom(it) }
    }

    override fun getMovies(): List<MovieEntity> {
        return api.getPopularMovies().movies.map { movieDataMapper.mapFrom(it) }
    }

    override fun getMovieById(movieId: Int): MovieEntity {
        return detailedDataMapper.mapFrom(api.getMovieDetails(movieId))
    }
}