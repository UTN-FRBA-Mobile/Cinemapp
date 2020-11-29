package com.utn.frba.cinemapp.presentation.ui.tickets

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
import com.utn.frba.cinemapp.domain.entities.tickets.TicketOutEntity
import kotlinx.android.synthetic.main.activity_tickets_item.view.*
import java.text.SimpleDateFormat

class ListTicketsAdapter(
    private var tickets: List<TicketOutEntity>,
    private var context: Context
) : RecyclerView.Adapter<ViewHolderTickets>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTickets {

        val inflater: View = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_tickets_item,
            parent,
            false
        )

        return ViewHolderTickets(inflater, this.context)
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolderTickets, position: Int) {
        holder.bind(tickets[position])
    }
}

class ViewHolderTickets(
    private var vista: View,
    private var context: Context
) : RecyclerView.ViewHolder(vista) {

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(ticket: TicketOutEntity) {

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        vista.title.text = sdf.format(ticket.purchaseDate)

        val finalUrl = URL_PROXY_IMAGES + ticket.movie.posterId.replace("/", "")
        Picasso.get().load(finalUrl).into(vista.ticket_image)

        vista.tickets_constraint_layout.setOnClickListener {

            val detailIntent = Intent(context, TicketQrActivity::class.java)
            detailIntent.putExtra(TicketQrActivity.KEY_TICKET_SELECTED_ID, ticket.qr.id)
            context.startActivity(detailIntent)
        }
    }
}