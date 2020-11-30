package com.utn.frba.cinemapp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.adaptadores.DescuentosAdapter
import com.utn.frba.cinemapp.models.Descuento
import kotlinx.android.synthetic.main.activity_descuentos.*
import okhttp3.*
import java.io.IOException

class DescuentosActivity : AppCompatActivity() {

    var descuentos: Array<Descuento>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descuentos)
        val client = OkHttpClient()
        val userToken = getUserToken();
        /*var desc: Descuento = Descuento("asd","asdasdasdas","as")
        var desc2: Descuento = Descuento("awwwwsd","asdasdasdas","as")

        val array = Array(6) {desc}
        showDescuentos(array)
        */
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
                    val gson = GsonBuilder().create()
                    val descuentos =
                        gson.fromJson(response.body()!!.string(), Array<Descuento>::class.java)
                    runOnUiThread(Runnable {
                        showDescuentos(descuentos)
                    })
                }
            }
        })

    }


    fun showDescuentos(descuentos: Array<Descuento> ) {
        this.descuentos = descuentos
        for (descuento in descuentos) {
            Log.v("descuentos", descuento.description)
        }
        descuentos_recyclerview.layoutManager = LinearLayoutManager(this)
        val adaptador = DescuentosAdapter(this.descuentos!!,this)
        descuentos_recyclerview.adapter = adaptador
    }

    private fun getUserToken() :String?{
        val prefs =
            getSharedPreferences("userToken", Context.MODE_PRIVATE)
        return prefs.getString("userToken", "")
    }
}