package com.utn.frba.cinemapp.data.api.entity.tickets

import com.google.gson.annotations.SerializedName
import com.utn.frba.cinemapp.data.api.entity.QrData
import java.util.*


data class TicketOutData(

    @SerializedName("id")
    var id: UUID,

    @SerializedName("cinema")
    var cinema: CinemaTicketOutData,

    @SerializedName("credit_card_number")
    var creditCard: String,

    @SerializedName("discounts")
    var discounts: List<UUID>,

    @SerializedName("movie")
    var movie: MovieTicketOutData,

    @SerializedName("price")
    var price: Float,

    @SerializedName("purchase_date")
    var purchaseDate: String,

    @SerializedName("qr")
    var qr: QrData,

    @SerializedName("user_id")
    var userId: UUID
)

data class CinemaTicketOutData(

    @SerializedName("adress")
    var address: String,

    @SerializedName("cinema_id")
    var cinemaId: UUID,

    @SerializedName("movie_date")
    var movieDate: String,

    @SerializedName("movie_time")
    var movieTime: String,

    @SerializedName("room")
    var room: Int,

    @SerializedName("seats")
    var seats: List<Int>,

    @SerializedName("name")
    var name: String
)

data class MovieTicketOutData(

    @SerializedName("id_poster_img")
    var posterId: String,

    @SerializedName("id_themoviedb")
    var movieId: Int,

    @SerializedName("name")
    var name: String
)
