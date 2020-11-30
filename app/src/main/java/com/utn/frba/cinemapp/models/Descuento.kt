package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class Descuento(@SerializedName("description") var description: String,
                     @SerializedName("discount_price") var discount_price: String,
                     @SerializedName("discount_percent") var discount_percent: String,
                     @SerializedName("id") var id: String)