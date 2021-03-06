package com.utn.frba.cinemapp.data.api.mappers

import com.utn.frba.cinemapp.data.api.entity.movies.MovieData
import com.utn.frba.cinemapp.domain.common.Mapper
import com.utn.frba.cinemapp.domain.entities.movies.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataEntityMapper @Inject constructor() : Mapper<MovieData, MovieEntity>() {

    override fun mapFrom(from: MovieData): MovieEntity {
        return MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalLanguage = from.originalLanguage,
            backdropPath = from.backdropPath,
            originalTitle = from.originalTitle,
            releaseDate = from.releaseDate,
            overview = from.overview,
            genreIds = from.genreIds
        )
    }
}
