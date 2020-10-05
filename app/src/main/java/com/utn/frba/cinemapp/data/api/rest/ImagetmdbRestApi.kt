package com.utn.frba.cinemapp.data.api.rest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImagetmdbRestApi {

    @GET("{imagePath}")
    fun getImage(@Path("imagePath") imagePath: String): Call<ResponseBody>
}