package com.utn.frba.cinemapp.domain.entities.tickets

import com.utn.frba.cinemapp.domain.entities.QrEntity
import java.util.*


data class TicketOutEntity(

    var id: UUID,
    var cinema: CinemaTicketOutEntity,
    var creditCard: String,
    var discounts: List<UUID>,
    var movie: MovieTicketOutEntity,
    var price: Float,
    var purchaseDate: Float,
    var qr: QrEntity,
    var userId: UUID
)

data class CinemaTicketOutEntity(
    var address: String,
    var cinemaId: UUID,
    var movieDate: Date,
    var movieTime: Timer,
    var room: Int,
    var seats: List<Int>,
    var name: String
)

data class MovieTicketOutEntity(
    var posterId: String,
    var movieId: Int,
    var name: String
)
