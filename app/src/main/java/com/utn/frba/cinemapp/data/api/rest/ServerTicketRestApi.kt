package com.utn.frba.cinemapp.data.api.rest

import com.utn.frba.cinemapp.data.api.entity.tickets.TicketInData
import com.utn.frba.cinemapp.data.api.entity.tickets.TicketOutData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface ServerTicketRestApi {

    @GET("api/v1/tickets")
    fun getTickets(): Call<List<TicketOutData>>

    @GET("api/v1/tickets/byToken")
    fun getTickets(@Query("token") token: UUID): Call<List<TicketOutData>>

    @POST("api/v1/tickets/byToken")
    fun buyTicket(
        @Query("token") token: UUID,
        @Body ticketIn: TicketInData
    ): Call<HashMap<String, UUID>>
}