package com.utn.frba.cinemapp.data.api.entity.tickets

import com.google.gson.annotations.SerializedName
import java.util.*


data class TicketInData(

    @SerializedName("movie_id")
    var movieId: Int,

    @SerializedName("cinema_id")
    var cinemaId: UUID,

    @SerializedName("movie_date")
    var movieDate: Date,

    @SerializedName("movie_time")
    var movieTime: Timer,

    @SerializedName("room")
    var room: Int,

    @SerializedName("seats")
    var seats: List<Int>,

    @SerializedName("discounts")
    var discounts: List<UUID> = listOf(),

    @SerializedName("credit_card_number")
    var creditCardNumber: String
)