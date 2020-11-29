package com.utn.frba.cinemapp.presentation.ui.detailMovies

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.domain.entities.ReviewEntity
import kotlinx.android.synthetic.main.activity_movie_detail_body_item_review.view.*

class ListReviewsAdapter(
    private var reviews: List<ReviewEntity>
) : RecyclerView.Adapter<ViewHolderReviews>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderReviews {

        val inflater: View = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_movie_detail_body_item_review,
            parent,
            false
        )

        return ViewHolderReviews(inflater)
    }

    override fun getItemCount(): Int {
        return this.reviews.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolderReviews, position: Int) {
        holder.bind(this.reviews[position])
    }
}

class ViewHolderReviews(
    private var vista: View
) : RecyclerView.ViewHolder(vista) {

    fun bind(review: ReviewEntity) {

        vista.review_author.text = review.author
        vista.review_content.text = review.content

    }
}