package com.utn.frba.cinemapp.presentation.ui.popularMovies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import kotlinx.android.synthetic.main.popular_movies_item.view.*

class ListMoviesAdapter(
    private var listMovies: List<MovieEntity>,
    private var context: Context
) : RecyclerView.Adapter<ListMoviesAdapter.ViewHolder>() {

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    class ViewHolder(private var vista: View, private var context: Context) :

        RecyclerView.ViewHolder(vista) {
        fun bind(movie: MovieEntity) {
            vista.movie_image.setImageBitmap(movie.posterBitMap)
            vista.title.text = movie.title
            vista.genre.text = movie.details?.genres?.joinToString { it.name + ", " }
            vista.stars.rating = movie.popularity.toFloat()

//            vista.elpIvPelicula.setOnClickListener {
//                context.startActivity(
//                    Intent(context, VisorImagen::class.java).putExtra(
//                        "pel",
//                        movie
//                    )
//                )
//            }
//
//            vista.setOnClickListener {
//                context.startActivity(
//                    Intent(context, Detalles::class.java).putExtra(
//                        "pel",
//                        movie
//                    )
//                )
//            }
        }
    }
}