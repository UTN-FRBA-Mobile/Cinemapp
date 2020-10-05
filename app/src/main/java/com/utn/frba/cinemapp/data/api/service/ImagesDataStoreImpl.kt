package com.utn.frba.cinemapp.data.api.service

import android.graphics.Bitmap
import com.utn.frba.cinemapp.config.URL_BACKEND
import com.utn.frba.cinemapp.data.api.rest.ImagetmdbRestApi
import com.utn.frba.cinemapp.domain.servicies.ImagesDataStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesDataStoreImpl : ImagesDataStore {

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BACKEND)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiRest = retrofit.create<ImagetmdbRestApi>(ImagetmdbRestApi::class.java)

    override fun getImage(imagePath: String): Bitmap? {
        return apiRest.getImage(imagePath)
    }

}