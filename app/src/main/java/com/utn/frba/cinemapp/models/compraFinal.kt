package com.utn.frba.cinemapp.models

import java.io.Serializable

data class compraFinal(
    var movie_id: Int = 0,
    var cinema_id: String? = "",
    var movie_date: String? = "",
    var movie_time: String? = "",
    var room: Int? = 1,
    var seats: List<Int>? = emptyList(),
    var discounts: List<String>?,
    var credit_card_number: String? = ""
): Serializable
