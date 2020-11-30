package com.utn.frba.cinemapp.data.api.mappers

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.utn.frba.cinemapp.data.api.entity.tickets.TicketOutData
import com.utn.frba.cinemapp.domain.common.Mapper
import com.utn.frba.cinemapp.domain.entities.QrEntity
import com.utn.frba.cinemapp.domain.entities.tickets.CinemaTicketOutEntity
import com.utn.frba.cinemapp.domain.entities.tickets.MovieTicketOutEntity
import com.utn.frba.cinemapp.domain.entities.tickets.TicketOutEntity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketOutDataEntityMapper @Inject constructor() : Mapper<TicketOutData, TicketOutEntity>() {

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun mapFrom(from: TicketOutData): TicketOutEntity {

        val cinema = CinemaTicketOutEntity(
            address = from.cinema.address,
            cinemaId = from.cinema.cinemaId,
            movieDate = LocalDate.parse(from.cinema.movieDate),
            movieTime = LocalTime.parse(from.cinema.movieTime),
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
            qr = qr,
            purchaseDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(from.purchaseDate),
            userId = from.userId
        )
    }
}
