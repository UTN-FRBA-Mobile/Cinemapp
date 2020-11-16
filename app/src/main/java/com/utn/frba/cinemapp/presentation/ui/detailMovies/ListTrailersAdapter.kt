package com.utn.frba.cinemapp.presentation.ui.detailMovies

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.config.URL_PROXY_YOUTUBE_IMG
import com.utn.frba.cinemapp.config.URL_PROXY_YOUTUBE_WATCH
import com.utn.frba.cinemapp.domain.entities.VideoEntity
import kotlinx.android.synthetic.main.activity_movie_detail_body_item_video.view.*

class ListTrailersAdapter(
    private var videos: List<VideoEntity>,
    private var context: Context
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater: View = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_movie_detail_body_item_video,
            parent,
            false
        )

        return ViewHolder(inflater, context)
    }

    override fun getItemCount(): Int {
        return this.videos.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.videos[position])
    }
}

class ViewHolder(
    private var vista: View,
    private var context: Context
) : RecyclerView.ViewHolder(vista) {

    fun bind(video: VideoEntity) {

        vista.item_video_title.text = video.name

        val finalUrl = "${URL_PROXY_YOUTUBE_IMG}${video.youtubeKey}/0.jpg"
        Picasso.get().load(finalUrl).into(vista.item_video_cover)

        vista.item_video_cover.setOnClickListener {

            val youtubeWatchUrl = "${URL_PROXY_YOUTUBE_WATCH}${video.youtubeKey}"

            val openURL = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(youtubeWatchUrl)
            )
            context.startActivity(openURL)
        }
    }
}