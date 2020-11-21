package com.utn.frba.cinemapp.domain.entities

import java.io.Serializable


data class ReviewEntity(
    var id: String,
    var author: String,
    var content: String? = null
) : Serializable