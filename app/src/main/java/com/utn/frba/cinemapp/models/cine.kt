package com.utn.frba.cinemapp.models

import com.google.gson.annotations.SerializedName

data class cine(@SerializedName("adress") var adress: String,
                @SerializedName("description") var description: String,
                @SerializedName("id") var identificador: String,
                @SerializedName("name") var name: String,
                @SerializedName("stars") var stars: String)
