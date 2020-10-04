package com.utn.frba.cinemapp.presentation.ui.popularMovies

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import kotlinx.android.synthetic.main.popular_movies_activity.*

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movies_activity)


        val moviesMock = ArrayList<MovieEntity>()
        moviesMock.add(
            MovieEntity(
                title = "asd",
                posterPath = "asd",
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.film),
                originalLanguage = "asd",
                originalTitle = "asd",
                backdropPath = "asd",
                releaseDate = "asd",
                overview = "asd"
            )
        )
        moviesMock.add(
            MovieEntity(
                title = "qwe",
                posterPath = "qwe",
                posterBitMap = BitmapFactory.decodeResource(resources, R.drawable.iniciar_sesion),
                originalLanguage = "qw",
                originalTitle = "qwe",
                backdropPath = "qwe",
                releaseDate = "qwe",
                overview = "qwe"
            )
        )

        popular_movies_recyclerview.layoutManager = LinearLayoutManager(this)
        popular_movies_recyclerview.adapter = ListMoviesAdapter(moviesMock, this)

    }
}