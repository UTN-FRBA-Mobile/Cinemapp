package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class Descuento(@SerializedName("description") var description: String,
                     @SerializedName("discount_price") var discount_price: String,
                     @SerializedName("id") var id: String)