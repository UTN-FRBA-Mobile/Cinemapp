package com.utn.frba.cinemapp.data.api.mappers

import com.utn.frba.cinemapp.data.api.entity.tickets.TicketOutData
import com.utn.frba.cinemapp.domain.common.Mapper
import com.utn.frba.cinemapp.domain.entities.QrEntity
import com.utn.frba.cinemapp.domain.entities.tickets.CinemaTicketOutEntity
import com.utn.frba.cinemapp.domain.entities.tickets.MovieTicketOutEntity
import com.utn.frba.cinemapp.domain.entities.tickets.TicketOutEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketOutDataEntityMapper @Inject constructor() : Mapper<TicketOutData, TicketOutEntity>() {

    override fun mapFrom(from: TicketOutData): TicketOutEntity {

        val cinema = CinemaTicketOutEntity(
            address = from.cinema.address,
            cinemaId = from.cinema.cinemaId,
            movieDate = from.cinema.movieDate,
            movieTime = from.cinema.movieTime,
            name = from.cinema.name,
            room = from.cinema.room,
            seats = from.cinema.seats
        )

        val movie = MovieTicketOutEntity(
            posterId = from.movie.posterId,
            movieId = from.movie.movieId,
            name = from.movie.name
        )

        val qr = QrEntity(
            id = from.qr.id
        )

        return TicketOutEntity(
            cinema = cinema,
            creditCard = from.creditCard,
            discounts = from.discounts,
            id = from.id,
            movie = movie,
            price = from.price,
            purchaseDate = from.purchaseDate,
            qr = qr,
            userId = from.userId
        )
    }
}
