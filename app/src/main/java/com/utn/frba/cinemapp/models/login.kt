package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class login(
    @SerializedName("user") var user: String?,
    @SerializedName("password") var password: String?,
    @SerializedName("token") var token: String?)
