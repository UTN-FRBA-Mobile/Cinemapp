package com.utn.frba.cinemapp.data.api.service

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.utn.frba.cinemapp.config.URL_BACKEND
import com.utn.frba.cinemapp.data.api.entity.tickets.TicketInData
import com.utn.frba.cinemapp.data.api.entity.tickets.TicketOutData
import com.utn.frba.cinemapp.data.api.mappers.TicketOutDataEntityMapper
import com.utn.frba.cinemapp.data.api.rest.ServerTicketRestApi
import com.utn.frba.cinemapp.domain.entities.tickets.TicketInEntity
import com.utn.frba.cinemapp.domain.entities.tickets.TicketOutEntity
import com.utn.frba.cinemapp.domain.servicies.TicketsDataStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TicketsDataStoreImpl : TicketsDataStore {

    private val ticketsMapper = TicketOutDataEntityMapper()

    private val restServer = Retrofit.Builder()
        .baseUrl(URL_BACKEND)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<ServerTicketRestApi>(ServerTicketRestApi::class.java)


    override fun getTicketsAsync(
        onSuccess: (List<TicketOutEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        restServer.getTickets().enqueue(object : Callback<List<TicketOutData>> {

            override fun onFailure(call: Call<List<TicketOutData>>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(
                call: Call<List<TicketOutData>>,
                response: Response<List<TicketOutData>>
            ) {
                Log.v("getTicketsAsync", response.body().toString())
                val r = response.body() as List<*>
                onSuccess(r.map { ticketsMapper.mapFrom(it as TicketOutData) })
            }
        })
    }

    override fun getTicketsAsync(
        token: UUID,
        onSuccess: (List<TicketOutEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        restServer.getTickets(token).enqueue(object : Callback<List<TicketOutData>> {

            override fun onFailure(call: Call<List<TicketOutData>>, t: Throwable) {
                onError(t)
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<List<TicketOutData>>,
                response: Response<List<TicketOutData>>
            ) {
                Log.v("getTicketsAsync", response.body().toString())
                val r = mutableListOf<TicketOutData>()
                if (response.body() != null) {
                    r.addAll(response.body() as List<TicketOutData>)
                }
                onSuccess(r.map { ticketsMapper.mapFrom(it) })
            }
        })
    }

    override fun buyTicketsAsync(
        token: UUID,
        ticketIn: TicketInEntity,
        onSuccess: (UUID) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {

        val ticketInData = TicketInData(
            movieId = ticketIn.movieId,
            cinemaId = ticketIn.cinemaId,
            movieDate = ticketIn.movieDate,
            movieTime = ticketIn.movieTime,
            room = ticketIn.room,
            seats = ticketIn.seats,
            creditCardNumber = ticketIn.creditCardNumber
        )

        restServer.buyTicket(token, ticketInData).enqueue(object : Callback<HashMap<String, UUID>> {

            override fun onFailure(call: Call<HashMap<String, UUID>>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(
                call: Call<HashMap<String, UUID>>,
                response: Response<HashMap<String, UUID>>
            ) {
                Log.v("buyTicket", response.body().toString())
                val r = response.body() as HashMap
                onSuccess(r["id"]!!)
            }
        })
    }


}
