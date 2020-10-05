package com.utn.frba.cinemapp.presentation.ui.popularMovies

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.data.api.service.ImagesDataStoreImpl
import com.utn.frba.cinemapp.data.api.service.MoviesDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.ImagesDataStore
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import kotlinx.android.synthetic.main.popular_movies_activity.*

class MoviesActivity : AppCompatActivity() {

    private val moviesDataStore: MoviesDataStore = MoviesDataStoreImpl()
    private val imagesDataStore: ImagesDataStore = ImagesDataStoreImpl()

    private lateinit var popularMovies: List<MovieEntity>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movies_activity)

        moviesDataStore.getPopularMoviesAsync(
            onSuccess = { loadPopularMoviesSuccess(it) },
            onError = { genericServiceError(it) })
    }

    private fun loadPopularMoviesSuccess(movies: List<MovieEntity>) {

        popularMovies = movies
        Log.v("movies", popularMovies.toString())

        popularMovies.forEach {
            imagesDataStore.getImageAsync(
                it.posterPath!!,
                onSuccess = { r -> loadImagesMoviesSuccess(it.id, r) },
                onError = { t -> genericServiceError(t) }
            )
        }
    }

    private fun loadImagesMoviesSuccess(movieId: Int, image: Bitmap?) {

        val movie = this.popularMovies.findLast { it.id == movieId }
        movie?.posterBitMap = image

        popular_movies_recyclerview.layoutManager = LinearLayoutManager(this)
        popular_movies_recyclerview.adapter = ListMoviesAdapter(this.popularMovies, this)
    }


    private fun genericServiceError(t: Throwable) {
        Log.v(t.localizedMessage, t.stackTrace.toString())
    }

}