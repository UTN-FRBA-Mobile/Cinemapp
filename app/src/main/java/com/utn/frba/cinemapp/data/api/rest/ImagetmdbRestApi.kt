package com.utn.frba.cinemapp.data.api.rest

import android.graphics.Bitmap
import retrofit2.http.GET
import retrofit2.http.Path

interface ImagetmdbRestApi {

    @GET("/{imagePath}")
    fun getImage(@Path("imagePath") imagePath: String): Bitmap
}