package com.utn.frba.cinemapp.domain.servicies

import com.utn.frba.cinemapp.domain.entities.tickets.TicketInEntity
import com.utn.frba.cinemapp.domain.entities.tickets.TicketOutEntity
import java.util.*

interface TicketsDataStore {

    /**
     *
     */
    fun getTicketsAsync(
        onSuccess: (List<TicketOutEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    )

    /**
     *
     */
    fun getTicketsAsync(
        token: UUID,
        onSuccess: (List<TicketOutEntity>) -> Unit,
        onError: (t: Throwable) -> Unit
    )

    /**
     *
     */
    fun buyTicketsAsync(
        token: UUID,
        ticketIn: TicketInEntity,
        onSuccess: (UUID) -> Unit,
        onError: (t: Throwable) -> Unit
    )
}