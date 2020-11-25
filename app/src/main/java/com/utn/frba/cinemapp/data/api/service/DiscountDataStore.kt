package com.utn.frba.cinemapp.data.api.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.utn.frba.cinemapp.config.URL_PROXY_IMAGES
import com.utn.frba.cinemapp.data.api.rest.ImagetmdbRestApi
import com.utn.frba.cinemapp.domain.servicies.ImagesDataStore
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscountDataStore  {

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_PROXY_IMAGES)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiRest = retrofit.create<ImagetmdbRestApi>(ImagetmdbRestApi::class.java)

    fun getImageAsync(
        imagePath: String,
        onSuccess: (Bitmap?) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {

        apiRest.getImage(imagePath).enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                onSuccess(BitmapFactory.decodeStream(response.body()!!.byteStream()))
            }
        })
    }
}