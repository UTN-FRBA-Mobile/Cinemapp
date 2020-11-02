package com.utn.frba.cinemapp.presentation.ui.detailMovies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.data.api.service.MoviesDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE_SELECTED = "KEY_ID_SELECTED"
    }

    private val moviesDataStore: MoviesDataStore = MoviesDataStoreImpl()
    private lateinit var movie: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        this.movie = intent.getSerializableExtra(KEY_MOVIE_SELECTED) as MovieEntity
        Log.i("movie selected", movie.toString())

        moviesDataStore.getDetailMovieAsync(
            movie.id,
            onSuccess = { loadDetailMovieSuccess(it) },
            onError = { genericServiceError(it) })
    }

    private fun loadDetailMovieSuccess(movie: MovieEntity) {

        this.movie = movie
        Log.i("SELECTED", movie.toString())

        // TODO: hacer que funcione la vista
        val toolbar: Toolbar = findViewById<View>(R.id.appBarLayout) as Toolbar
        toolbar.title = "This is toolbar."
        setSupportActionBar(toolbar)
    }

    private fun genericServiceError(t: Throwable) {
        Log.e(t.localizedMessage, t.stackTrace.toString())
    }

}