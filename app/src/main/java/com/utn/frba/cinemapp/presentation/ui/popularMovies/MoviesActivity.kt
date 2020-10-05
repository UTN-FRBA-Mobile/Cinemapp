package com.utn.frba.cinemapp.presentation.ui.popularMovies

import android.graphics.BitmapFactory
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movies_activity)

        popular_movies_recyclerview.layoutManager = LinearLayoutManager(this)

        moviesDataStore.getPopularMoviesAsync(
            onSuccess = { loadPopularMoviesSuccess(it) },
            onError = { genericServiceError(it) })
    }

    private fun loadPopularMoviesSuccess(movies: List<MovieEntity>) {
        Log.v("movies", movies.toString())
        for (m in movies) {
            m.posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.film)
        }
        popular_movies_recyclerview.adapter = ListMoviesAdapter(movies, this)
    }

    private fun genericServiceError(t: Throwable) {
        Log.v(t.localizedMessage, t.stackTrace.toString())
    }

}