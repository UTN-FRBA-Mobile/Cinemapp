package com.utn.frba.cinemapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.utn.frba.cinemapp.MainActivity
import com.utn.frba.cinemapp.R
import kotlinx.android.synthetic.main.activity_scan_ready.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class ScanReadyActivity : AppCompatActivity() {

    private var title: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_ready)
        title = findViewById(R.id.textView3)
        val b = intent
        var code = b.getStringExtra("code")
        code = "bf142b24-a528-4ac6-a444-0986aa2dcc1a" //mock
        title!!.text = "Aguarde por favor..."
        val userToken = "f68c6b39-f657-4ad9-928d-27dfbb26f4cc" //mock
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
                Log.v("SUC", response.toString())
                Log.v("SUCCC", response.body()!!.string())

               /* if (response.isSuccessful) {
                    val myResponse: String = response.body()!!.string()
                    val mockedResponse = "20"
                    Log.v("response", myResponse)
                    title!!.text = "Su descuento  de se ha acreditado con exito"

                    val url =
                        "https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/discounts/byToken?token=$userToken"

                    val request: Request = Request.Builder()
                        .url(url)
                        .get()
                        .build()
                    val response = client.newCall(request).execute()

                    showDiscounts(response)
                }*/
            }
        })
        setupButtons()


    }

    private fun showDiscounts(response: Response){
        Log.v("response", response.toString())
    }

    private fun setupButtons(){
        optionScan.setOnClickListener {
            val scanIntent = Intent(this, ScanActivity::class.java)
            startActivity(scanIntent)
        }

        optionHome.setOnClickListener{
            val scanIntent = Intent(this, MainActivity::class.java)
            startActivity(scanIntent)
        }
    }

}