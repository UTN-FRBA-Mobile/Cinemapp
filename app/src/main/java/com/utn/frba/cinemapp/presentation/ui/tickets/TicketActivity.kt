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
import kotlinx.android.synthetic.main.activity_tickets.*
import java.util.*

class TicketActivity : AppCompatActivity() {

    private val ticketsDataStore: TicketsDataStore = TicketsDataStoreImpl()

    private lateinit var tickets: List<TicketOutEntity>

    //    private var token: UUID = UUID.randomUUID()
    private lateinit var token: UUID

    override fun onCreate(savedInstanceState: Bundle?) {

        this.token = intent.getSerializableExtra("token") as UUID

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        ticketsDataStore.getTicketsAsync(
            token = this.token,
            onSuccess = { loadTicketsSuccess(it) },
            onError = { genericServiceError(it) })
    }

    private fun loadTicketsSuccess(tickets: List<TicketOutEntity>) {

        this.tickets = tickets
        tickets_progress.visibility = View.INVISIBLE

        tickets_recyclerview.layoutManager = LinearLayoutManager(this)
        tickets_recyclerview.adapter = ListTicketsAdapter(this.tickets, this)
    }

    private fun genericServiceError(t: Throwable) {
        Log.e("t.localizedMessage", t.toString())
    }

}