package com.utn.frba.cinemapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.utn.frba.cinemapp.MainActivity
import com.utn.frba.cinemapp.R
import kotlinx.android.synthetic.main.activity_main.optionScan
import kotlinx.android.synthetic.main.activity_scan_ready.*
import okhttp3.*
import java.io.IOException


class ScanReadyActivity : AppCompatActivity() {

    private var title: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_ready)
        title = findViewById(R.id.textView3)
        val b = intent
        val code = b.getStringExtra("code")
        title!!.text = "Aguarde por favor..."

        val client = OkHttpClient()
        val url = "https://reqres.in/api/users?page=2"
        val request: Request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                e.printStackTrace()
                setupButtons()
            }

            override fun onResponse(call: Call?, response: Response) {
                if (response.isSuccessful) {
                    val myResponse: String = response.body()!!.string()
                    val mockedResponse = "20"
                    Log.v("response", myResponse)

                    saveDiscount(mockedResponse.toInt())
                }
                setupButtons()
            }
        })
        setupButtons()


    }

    private fun saveDiscount(discount: Int){
        title!!.text = "Su descuento  de " + discount + "% se ha acreditado con exito"
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