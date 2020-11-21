package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.utn.frba.cinemapp.models.compra
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pago.*

class Pago : AppCompatActivity() {
    private var contadorCaracteres = 0
    lateinit var compraTicket: compra

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        //Obtengo los datos que vengo arrastrando con la compra
        val bundle: Bundle? = intent.extras;
        compraTicket = bundle?.getSerializable("compra") as compra

        println(compraTicket)



        setup();
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
}