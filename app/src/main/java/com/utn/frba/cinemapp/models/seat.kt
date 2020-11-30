package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class seat(@SerializedName("enable") var status: Boolean,
                @SerializedName("name") var name: String)
