package com.utn.frba.cinemapp.presentation.ui.detailMovies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.config.URL_PROXY_IMAGES
import com.utn.frba.cinemapp.data.api.service.MoviesDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import kotlinx.android.synthetic.main.activity_movie_detail_body.view.*
import kotlinx.android.synthetic.main.activity_movie_detail_header.view.*
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
        Log.i("MOVIE", movie.toString())

        renderDetailHeader()
        renderCollapsingLayout()
        renderSinopsis()
        renderTrailers()

        movie_detail_main.visibility = View.VISIBLE
        movie_detail_progress.visibility = View.INVISIBLE
    }

    private fun genericServiceError(t: Throwable) {
        Log.e(t.localizedMessage, t.stackTrace.toString())
    }

    @SuppressLint("SimpleDateFormat")
    private fun renderDetailHeader() {

        val sdf = SimpleDateFormat("dd/MM/yyyy")

        detail_header.detail_header_title.text = this.movie.title
        detail_header.detail_header_release_date.text = sdf.format(this.movie.releaseDate)
        detail_header.detail_header_genre.text =
            this.movie.details?.genres?.joinToString { it.name }
        detail_header.detail_header_star.rating =
            this.movie.voteAverage.toFloat() * 0.5f
    }

    private fun renderCollapsingLayout() {

        app_bar_layout.movie_detail_collapsing_toolbar.title = this.movie.title

        val finalUrl = URL_PROXY_IMAGES + this.movie.backdropPath?.replace("/", "")
        Picasso.get().load(finalUrl).into(app_bar_layout.movie_detail_poster)
    }

    private fun renderSinopsis() {
        detail_body.detail_body_overwiev_text.text = this.movie.details?.overview
    }

    private fun renderTrailers() {
        detail_body.detail_body_recyclerView_trailers.layoutManager = LinearLayoutManager(this)
        detail_body.detail_body_recyclerView_trailers.adapter =
            ListTrailersAdapter(this.movie.details?.videos!!)
    }

}