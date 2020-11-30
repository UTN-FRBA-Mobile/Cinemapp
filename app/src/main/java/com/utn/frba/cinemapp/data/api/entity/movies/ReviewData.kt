package com.utn.frba.cinemapp.data.api.entity.movies

data class ReviewData(
    var id: String,
    var author: String,
    var content: String? = null
)