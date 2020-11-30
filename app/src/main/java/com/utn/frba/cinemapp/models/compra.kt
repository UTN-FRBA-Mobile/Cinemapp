package com.utn.frba.cinemapp.models

import java.io.Serializable

data class compra(var idCine: String = "",
                  var horario: String? = "",
                  var listaAsientos: List<Int> = emptyList(),
                  var numeroTarjeta: String = "",
                  var idUsuario: String = "",
                  var email: String = "",
                  var idPelicula: Int = 0,
                  var dia: String? = "",
                  var hora: String? = "",
                  var precio: Int? = 0
): Serializable
