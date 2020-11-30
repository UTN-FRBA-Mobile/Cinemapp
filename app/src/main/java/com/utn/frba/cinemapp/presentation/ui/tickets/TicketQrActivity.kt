package com.utn.frba.cinemapp.presentation.ui.tickets

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.config.URL_BACKEND
import kotlinx.android.synthetic.main.activity_tickets_qr.*
import java.util.*

class TicketQrActivity : AppCompatActivity() {

    private lateinit var qrId: UUID

    companion object {
        const val KEY_TICKET_SELECTED_ID = "KEY_TICKET_SELECTED_ID"
        const val KEY_COMPRA = "compra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets_qr)

        this.qrId = intent.getSerializableExtra(KEY_TICKET_SELECTED_ID) as UUID
        renderQr()
    }

    private fun renderQr() {

        val finalUrl = "${URL_BACKEND}api/v1/qr/${this.qrId.toString().replace("/", "")}"
        Log.i("URL QR TICKET", finalUrl)
        Picasso.get().load(finalUrl).into(ticket_image_qr)
    }

}