package com.utn.frba.cinemapp.data.api.mappers

import com.utn.frba.cinemapp.data.api.entity.MovieDetailsData
import com.utn.frba.cinemapp.domain.common.Mapper
import com.utn.frba.cinemapp.domain.entities.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsMovieDataEntityMapper @Inject constructor() : Mapper<MovieDetailsData, MovieEntity>() {

    override fun mapFrom(from: MovieDetailsData): MovieEntity {

        val details = MovieDetailsEntity(
            overview = from.overview,
            budget = from.budget,
            homepage = from.homepage,
            imdbId = from.imdbId,
            revenue = from.revenue,
            runtime = from.runtime,
            tagline = from.tagline,
            genres = from.genres?.map { genreData ->
                GenreEntity(id = genreData.id, name = genreData.name)
            },
            videos = from.videos?.results?.map { videoData ->
                VideoEntity(
                    id = videoData.id,
                    name = videoData.name,
                    youtubeKey = videoData.key
                )
            },
            reviews = from.reviews?.results?.map { reviewData ->
                ReviewEntity(
                    id = reviewData.id,
                    author = reviewData.author,
                    content = reviewData.content
                )
            }
        )

        return MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            originalLanguage = from.originalLanguage,
            releaseDate = from.releaseDate,
            overview = from.overview,
            details = details
        )
    }

}