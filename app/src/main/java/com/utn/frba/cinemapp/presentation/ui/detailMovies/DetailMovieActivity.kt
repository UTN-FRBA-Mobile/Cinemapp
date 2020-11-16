package com.utn.frba.cinemapp.presentation.ui.detailMovies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.data.api.service.MoviesDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.layout_movie_detail_header.view.*
import java.text.SimpleDateFormat


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE_SELECTED_ID = "KEY_ID_SELECTED_ID"
    }

    private val moviesDataStore: MoviesDataStore = MoviesDataStoreImpl()
    private lateinit var movie: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movie_detail_main.visibility = View.INVISIBLE

        val selectedMovieId = intent.getSerializableExtra(KEY_MOVIE_SELECTED_ID) as Int

        moviesDataStore.getDetailMovieAsync(
            selectedMovieId,
            onSuccess = { loadDetailMovieSuccess(it) },
            onError = { genericServiceError(it) })
    }

    private fun loadDetailMovieSuccess(movie: MovieEntity) {

        this.movie = movie
        Log.i("DETAILS", movie.toString())

        renderDetailHeader()

        movie_detail_main.visibility = View.VISIBLE
        movie_detail_progress.visibility = View.INVISIBLE

        // TODO: hacer que funcione la vista
//        val toolbar: Toolbar = findViewById<View>(R.id.appBarLayout) as Toolbar
//        toolbar.title = "This is toolbar."
//        setSupportActionBar(toolbar)
    }

    private fun genericServiceError(t: Throwable) {
        Log.e(t.localizedMessage, t.stackTrace.toString())
    }

    @SuppressLint("SimpleDateFormat")
    private fun renderDetailHeader() {

        val sdf = SimpleDateFormat("dd/MM/yyyy")

        detail_header.detail_header_title.text = this.movie.title
        detail_header.detail_header_release_date.text = sdf.format(this.movie.releaseDate)
        detail_header.detail_header_genre.text = this.movie.details?.genres?.joinToString { it.name }
    }

}