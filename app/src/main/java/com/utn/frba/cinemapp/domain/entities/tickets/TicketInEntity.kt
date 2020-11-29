package com.utn.frba.cinemapp.domain.entities.tickets

import java.io.Serializable
import java.util.*


data class TicketInEntity(
    var movieId: Int,
    var cinemaId: UUID,
    var movieDate: Date,
    var movieTime: Timer,
    var room: Int,
    var seats: List<Int>,
    var discounts: List<UUID>,
    var creditCardNumber: String
) : Serializable
