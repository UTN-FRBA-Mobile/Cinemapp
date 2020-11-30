package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.utn.frba.cinemapp.interfaces.CinesApi
import com.utn.frba.cinemapp.models.compra
import com.utn.frba.cinemapp.models.compraFinal
import com.utn.frba.cinemapp.models.respuestaCompra
import com.utn.frba.cinemapp.presentation.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pago.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Pago : AppCompatActivity() {
    private var contadorCaracteres = 0
    lateinit var compraTicket: compra
    lateinit var retrofit: Retrofit
    lateinit var servicioApi: CinesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        //Obtengo los datos que vengo arrastrando con la compra
        val bundle: Bundle? = intent.extras;
        compraTicket = bundle?.getSerializable("compra") as compra

        //Servicio para consumir del backend
        retrofit = Retrofit.Builder()
            .baseUrl("https://utn-2020-2c-desa-mobile.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // creo el servicio para hacer las llamadas
        servicioApi = retrofit.create<CinesApi>(CinesApi::class.java)

        println(compraTicket)
        setup();
        setupButtonAcept()
    }

    private fun setup(){

        PagoTextNumberCard.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var numeroTarjeta: String = PagoTextNumberCard.text.toString()
                    PagoViewTextNumberCard.text = numeroTarjeta
            }
        })




    }

    private fun setupButtonAcept(){
        PagoSelectButton.setOnClickListener{
            var compraFinal: compraFinal = compraFinal(movie_id = compraTicket.idPelicula!!.toInt(),

            cinema_id = compraTicket.idCine.toString(),
            movie_date = compraTicket.dia.toString(),
            movie_time = compraTicket.hora.toString(),
            room = 1,
            seats = compraTicket.listaAsientos,
            discounts = emptyList(),
            credit_card_number = PagoTextNumberCard.text.toString())

            servicioApi.comprar(compraTicket.idUsuario.toString(),compraFinal ).enqueue(object :
                Callback<respuestaCompra> {

                override fun onFailure(call: Call<respuestaCompra>, t: Throwable) {
                    mostrarAlerta("Error al hacer la compra",0)
                }

                override fun onResponse(call: Call<respuestaCompra>?, response: Response<respuestaCompra>?) {
                    try{
                        val compraRealizada = response?.body()
                        if(compraRealizada != null){
                            onResultCompra(compraRealizada)
                        }

//                    Log.d("Debug", " Sarasa " + Gson().toJson(asientos))
                    }
                    catch (e: Exception){
                        //Log.i("sarasa", e.ge())
                    }
                }
            })



        }
    }

    private fun onResultCompra(compraRealizada: respuestaCompra){
        //mostrarAlerta("compra realizada con éxito",1)
        Toast.makeText(this, "COMPRA REALIZADA CON ÉXITO", Toast.LENGTH_LONG).show();
        var Inicio = Intent(this, MainActivity::class.java).apply { }
        var nuevaCompra = compra(idUsuario = compraTicket.idUsuario.toString(), email = compraTicket.email)

        Inicio.putExtra("compra",nuevaCompra)
        startActivity(Inicio);

    }

    private fun mostrarAlerta( texto: String, tipoError: Int){
        var builder = AlertDialog.Builder(this);
        if(tipoError == 1){
            builder.setTitle("Correcto");
        }
        else{
            builder.setTitle("Error");
        }
        builder.setTitle("Error");
        builder.setMessage(texto);
        builder.setPositiveButton("Aceptar", null);
        var dialog: AlertDialog = builder.create();
        dialog.show();
    }
}