package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class CreditCard(
    @SerializedName("number") var number: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("expiration") var expiration: String?)
