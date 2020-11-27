package com.utn.frba.cinemapp.models

import java.io.Serializable

data class compra(var idCine: String = "",
                  var horario: String? = "",
                  var listaAsientos: List<String> = emptyList(),
                  var numeroTarjeta: String = "",
                  var idUsuario: String = "",
                  var email: String = ""
): Serializable
