package com.utn.frba.cinemapp.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.utn.frba.cinemapp.Pago
import com.utn.frba.cinemapp.R
import com.utn.frba.cinemapp.models.Descuento
import com.utn.frba.cinemapp.models.Precio
import com.utn.frba.cinemapp.models.compra
import kotlinx.android.synthetic.main.activity_confirmacion_compra.*
import kotlinx.android.synthetic.main.activity_scan_ready.*
import kotlinx.android.synthetic.main.activity_scan_ready.optionDescuentos
import kotlinx.android.synthetic.main.activity_select_seat_time.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ConfirmacionActivity : AppCompatActivity() {

    private var title: TextView? = null
    private var compraTicket: compra? = null
    private var PagoIntent: Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion_compra)
        title = findViewById(R.id.textView3)

        val bundle: Bundle? = intent.extras;

        if (bundle != null) {
            compraTicket = bundle.getSerializable("compra") as compra
        }

        val client = OkHttpClient()
        val userToken = getUserToken();
        val url =
            "https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/tickets/price/byToken?token=$userToken"
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val data = JSONObject()
        data.put("movie_id", compraTicket!!.idPelicula)
        data.put("cinema_id", compraTicket!!.idCine)
        data.put("movie_date", compraTicket!!.dia)
        data.put("movie_time", compraTicket!!.hora)
        data.put("room", "1")
        data.put("credit_card_number", compraTicket!!.numeroTarjeta)
        val asientos = JSONArray(compraTicket!!.listaAsientos)
        data.put("seats", asientos)

        val body: RequestBody = RequestBody.create(JSON,data.toString())
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                Log.v("err", "ERRRR")
                e.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response) {

                if (response.isSuccessful) {
                    val gson = GsonBuilder().create()
                    val precioFinal =
                        gson.fromJson(response.body()!!.string(), Precio::class.java)
                    runOnUiThread(Runnable {
                        confirmarPago(precioFinal)
                    })
                }
            }
        })

        setupButtons()


    }


    private fun setupButtons(){

        optionDescuentos.setOnClickListener {
            val descuentosIntent = Intent(this, DescuentosActivity::class.java)
            startActivity(descuentosIntent)
        }
        pagarButton.setOnClickListener {
            startActivity(PagoIntent);
        }

    }

    private fun getUserToken() :String?{
        val prefs =
            getSharedPreferences("userToken", Context.MODE_PRIVATE)
        return prefs.getString("userToken", "")
    }

    private fun confirmarPago(precioFinal: Precio){
        title!!.text = "El precio final de su compra, con sus descuentos aplicados, es de un total de $" + precioFinal.price.toString() + ". Si quiere consultar los descuentos que se le van a aplicar, seleccione Ver Descuentos"
        compraTicket!!.precio = precioFinal.price
        PagoIntent = Intent(this, Pago::class.java).apply { }
        PagoIntent!!.putExtra("compra",compraTicket)
    }

}