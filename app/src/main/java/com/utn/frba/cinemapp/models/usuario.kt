package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class usuario(
    @SerializedName("user") var user: String?,
    @SerializedName("password") var password: String?)
