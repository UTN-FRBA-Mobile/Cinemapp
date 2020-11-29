package com.utn.frba.cinemapp.presentation.ui.tickets

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.data.api.service.TicketsDataStoreImpl
import com.utn.frba.cinemapp.domain.entities.tickets.TicketOutEntity
import com.utn.frba.cinemapp.domain.servicies.TicketsDataStore
import kotlinx.android.synthetic.main.activity_popular_movies.*
import kotlinx.android.synthetic.main.activity_tickets.*
import java.util.*

class TicketActivity : AppCompatActivity() {

    private val ticketsDataStore: TicketsDataStore = TicketsDataStoreImpl()

    private lateinit var tickets: List<TicketOutEntity>

    override fun onCreate(savedInstanceState: Bundle?) {

        val token = UUID.fromString("7801faea-11df-4b5e-b237-56758dbfe4f7")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        ticketsDataStore.getTicketsAsync(
            token = token,
            onSuccess = { loadTokenSuccess(it) },
            onError = { genericServiceError(it) })
    }

    private fun loadTokenSuccess(tickets: List<TicketOutEntity>) {

        this.tickets = tickets
        tickets_progress.visibility = View.INVISIBLE

        popular_movies_recyclerview.layoutManager = LinearLayoutManager(this)
        popular_movies_recyclerview.adapter = ListTicketsAdapter(this.tickets, this)
    }

    private fun genericServiceError(t: Throwable) {
        Log.e(t.localizedMessage, t.stackTrace.toString())
    }

}