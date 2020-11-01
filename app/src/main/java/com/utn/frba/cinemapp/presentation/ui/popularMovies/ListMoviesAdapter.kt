package com.utn.frba.cinemapp.presentation.ui.popularMovies

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.config.URL_PROXY_IMAGES
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.presentation.ui.detailMovies.DetailMovieActivity
import kotlinx.android.synthetic.main.popular_movies_item.view.*
import java.text.SimpleDateFormat

class ListMoviesAdapter(
    private var listMovies: List<MovieEntity>,
    private var context: Context
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater: View = LayoutInflater.from(parent.context).inflate(
            R.layout.popular_movies_item,
            parent,
            false
        )

        return ViewHolder(inflater, this.context)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }
}

class ViewHolder(
    private var vista: View,
    private var context: Context
) : RecyclerView.ViewHolder(vista) {

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(movie: MovieEntity) {

        val sdf = SimpleDateFormat("dd/MM/yyyy")

        vista.title.text = movie.title
        vista.genre.text = movie.details?.genres?.joinToString { it.name + ", " }
        vista.stars.rating = movie.voteAverage.toFloat() * 0.5f
        vista.releaseDate.text = sdf.format(movie.releaseDate)

        val finalUrl = URL_PROXY_IMAGES + movie.posterPath?.replace("/", "")
        Picasso.get().load(finalUrl).into(vista.movie_image)

        vista.popular_movies_constraint_layout.setOnClickListener {

            val detailIntent = Intent(context, DetailMovieActivity::class.java)
            detailIntent.putExtra(DetailMovieActivity.KEY_MOVIE_SELECTED, movie)
            context.startActivity(detailIntent)
        }
    }
}