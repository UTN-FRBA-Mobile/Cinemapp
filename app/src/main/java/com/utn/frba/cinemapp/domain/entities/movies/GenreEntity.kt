package com.utn.frba.cinemapp.domain.entities.movies

import java.io.Serializable


data class GenreEntity(
    var id: Int,
    var name: String
) : Serializable