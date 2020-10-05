package com.utn.frba.cinemapp.presentation.ui.popularMovies

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.data.api.service.ImagesDataStoreImpl
import com.utn.frba.cinemapp.data.api.service.MoviesDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.GenreEntity
import com.utn.frba.cinemapp.domain.entities.MovieDetailsEntity
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.ImagesDataStore
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import kotlinx.android.synthetic.main.popular_movies_activity.*

class MoviesActivity : AppCompatActivity() {

    private val moviesDataStore: MoviesDataStore = MoviesDataStoreImpl()
    private val imagesDataStore: ImagesDataStore = ImagesDataStoreImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movies_activity)

        popular_movies_recyclerview.layoutManager = LinearLayoutManager(this)
//        popular_movies_recyclerview.adapter = ListMoviesAdapter(mocksMovies(), this)
        popular_movies_recyclerview.adapter = ListMoviesAdapter(getMoviesWithImages(), this)

    }

    private fun getMoviesWithImages(): List<MovieEntity> {
        val movies = moviesDataStore.getPopularMovies()
        for (m in movies) {
            m.posterPath?.let {
                m.posterBitMap = imagesDataStore.getImage(it)
            }
        }
        return movies
    }

    private fun mocksMovies(): List<MovieEntity> {

        val genres = mutableListOf(GenreEntity(1, "Drama"), GenreEntity(1, "Suspenso"))

        return listOf(
            MovieEntity(
                title = "asd",
                posterPath = "asd",
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.film),
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
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.iniciar_sesion),
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
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.film),
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
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.iniciar_sesion),
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
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.film),
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
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.iniciar_sesion),
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