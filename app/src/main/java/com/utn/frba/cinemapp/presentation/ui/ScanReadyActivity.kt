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
import org.json.JSONArray
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
        val response = "[\n" +
                "    {\n" +
                "        \"description\": \"Descuento de \$100 en el total de tu próxima compra\",\n" +
                "        \"discount_price\": 100.0,\n" +
                "        \"id\": \"f6418fa7-f281-4f39-b85f-a5379c823e02\",\n" +
                "        \"qr\": {\n" +
                "            \"id\": \"153f27f3-a1d1-443b-9309-54b08d9dfa79\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"description\": \"Descuento de \$300 en el total de tu próxima compra\",\n" +
                "        \"discount_price\": 300.0,\n" +
                "        \"id\": \"a14a59ae-c3b5-44c9-9e00-360e25d5c70b\",\n" +
                "        \"qr\": {\n" +
                "            \"id\": \"9a5e1b04-bb93-4582-9143-51c3d700b33c\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"description\": \"Descuento del 10% del total de tu próxima compra\",\n" +
                "        \"discount_percent\": 10.0,\n" +
                "        \"id\": \"a20b2e1b-246a-4537-82e5-e57a41755a10\",\n" +
                "        \"qr\": {\n" +
                "            \"id\": \"a6e0a3fa-da8e-452a-a803-9e6ce41c5c3e\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"description\": \"Descuento de 20% en el total de tu próxima compra\",\n" +
                "        \"discount_percent\": 20.0,\n" +
                "        \"id\": \"2db0c6ed-315c-406e-a9dc-d2b1207f3053\",\n" +
                "        \"qr\": {\n" +
                "            \"id\": \"2e33e630-a760-4f54-92e7-c8fa9162f81e\"\n" +
                "        }\n" +
                "    }\n" +
                "]"
        val json = JSONObject(response)
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            val item = array.getJSONObject(i)
            val d = item.getString("description")
            Log.v("array",d)
        }


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