package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class register(
    @SerializedName("login") var login: usuario?,
    @SerializedName("creditCars") var creditCars: List<CreditCard>?,
    @SerializedName("id") var identificador: String?
)
