package com.utn.frba.cinemapp.presentation.ui.popularMovies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.data.api.service.MoviesDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.movies.GenreEntity
import com.utn.frba.cinemapp.domain.entities.movies.MovieDetailsEntity
import com.utn.frba.cinemapp.domain.entities.movies.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import kotlinx.android.synthetic.main.activity_popular_movies.*

class MoviesActivity : AppCompatActivity() {

    private val moviesDataStore: MoviesDataStore = MoviesDataStoreImpl()

    private lateinit var popularMovies: List<MovieEntity>
    private lateinit var genres: List<GenreEntity>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)

        moviesDataStore.getGenresAsync(
            onSuccess = { loadGenresSuccess(it) },
            onError = { genericServiceError(it) })
    }


    private fun loadGenresSuccess(genres: List<GenreEntity>) {

        this.genres = genres
        Log.i("genres", genres.toString())

        moviesDataStore.getPopularMoviesAsync(
            onSuccess = { loadPopularMoviesSuccess(it) },
            onError = { genericServiceError(it) })
    }

    private fun loadPopularMoviesSuccess(movies: List<MovieEntity>) {

        this.popularMovies = movies.sortedByDescending { it.voteAverage }

        for (pm in this.popularMovies) {

            pm.details = MovieDetailsEntity()
            pm.details?.genres = genres
                .filter { pm.genreIds?.contains(it.id)!! }
                .map { GenreEntity(it.id, it.name) }
        }

        Log.i("movies", popularMovies.toString())

        popular_movies_progress.visibility = View.INVISIBLE

        popular_movies_recyclerview.layoutManager = LinearLayoutManager(this)
        popular_movies_recyclerview.adapter = ListMoviesAdapter(this.popularMovies, this)
    }

    private fun genericServiceError(t: Throwable) {
        Log.e(t.localizedMessage, t.stackTrace.toString())
    }

}