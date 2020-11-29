package com.utn.frba.cinemapp.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.utn.frba.cinemapp.MainActivity
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.models.Descuento
//import kotlinx.android.synthetic.main.activity_main.optionScan
import kotlinx.android.synthetic.main.activity_scan_ready.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import com.utn.frba.cinemapp.models.*



class ScanReadyActivity : AppCompatActivity() {

    private var title: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_ready)
        title = findViewById(R.id.textView3)
        val b = intent
        var code = b.getStringExtra("code")
        title!!.text = "Aguarde por favor..."
        val userToken = getUserToken();
        val client = OkHttpClient()
        val url =
            "https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/discounts/byToken?token=$userToken"

        val formBody: RequestBody = FormBody.Builder()
            .add("id", code)
            .build()
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val data = JSONObject()
        data.put("id", code.toString())
        val body: RequestBody = RequestBody.create(JSON,data.toString())
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                Log.v("err", "ERRRR")
                e.printStackTrace()
                setupButtons()
            }

            override fun onResponse(call: Call?, response: Response) {

                if (response.isSuccessful) {
                    title!!.text = "Su descuento  de se ha acreditado con exito"

                   /* val url =
                        "https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/discounts/byToken?token=$userToken"

                    val request: Request = Request.Builder()
                        .url(url)
                        .get()
                        .build()
                    val response = client.newCall(request).execute()
                    val gson = GsonBuilder().create()
                    val descuentos = gson.fromJson(response.body()!!.string(), Array<Descuento>::class.java)
                    for (descuento in descuentos) {
                        Log.v("descuentos", descuento.description)
                    }

                    */

                }
            }
        })
        setupButtons()


    }


    private fun setupButtons(){

        optionDescuentos.setOnClickListener {
            val scanIntent = Intent(this, DescuentosActivity::class.java)
            startActivity(scanIntent)
        }

        optionScan.setOnClickListener {
            val scanIntent = Intent(this, ScanActivity::class.java)
            startActivity(scanIntent)
        }

        optionHome.setOnClickListener{
            val scanIntent = Intent(this, MainActivity::class.java)
            startActivity(scanIntent)
        }
    }

    private fun getUserToken() :String?{
        val prefs =
            getSharedPreferences("userToken", Context.MODE_PRIVATE)
        return prefs.getString("userToken", "")
    }

}