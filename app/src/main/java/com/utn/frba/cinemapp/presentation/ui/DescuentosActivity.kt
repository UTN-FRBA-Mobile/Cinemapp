package com.utn.frba.cinemapp.presentation.ui

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.models.Descuento
import okhttp3.*
import java.io.IOException

class DescuentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_descuentos)
        showDescuentos(this)
    }


    fun showDescuentos(context: Context) {
        val client = OkHttpClient()
        val userToken = getUserToken();

        val url =
            "https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/discounts/byToken?token=$userToken"

        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                Log.v("err", "ERRRR")
                e.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response) {

                if (response.isSuccessful) {
                    val response = client.newCall(request).execute()
                    val gson = GsonBuilder().create()
                    val descuentos =
                        gson.fromJson(response.body()!!.string(), Array<Descuento>::class.java)
                    for (descuento in descuentos) {
                        Log.v("descuentos", descuento.description)
                    }
                }
            }
        })
    }

    private fun getUserToken() :String?{
        val prefs =
            getSharedPreferences("userToken", Context.MODE_PRIVATE)
        return prefs.getString("userToken", "")
    }
}