package com.utn.frba.cinemapp.presentation.ui.detailMovies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.data.api.service.MoviesDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore

class DetailMovieActivity : AppCompatActivity() {

    private val moviesDataStore: MoviesDataStore = MoviesDataStoreImpl()

    private lateinit var movie: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

//        moviesDataStore.getPopularMoviesAsync(
//            onSuccess = { loadPopularMoviesSuccess(it) },
//            onError = { genericServiceError(it) })
    }

    private fun loadPopularMoviesSuccess(movies: List<MovieEntity>) {



//        this.popularMovies = movies.sortedByDescending { it.voteAverage }
//        Log.i("movies", popularMovies.toString())
//
//        popular_movies_recyclerview.layoutManager = LinearLayoutManager(this)
//        popular_movies_recyclerview.adapter = ListMoviesAdapter(this.popularMovies, this)
    }

    private fun genericServiceError(t: Throwable) {
        Log.e(t.localizedMessage, t.stackTrace.toString())
    }

}