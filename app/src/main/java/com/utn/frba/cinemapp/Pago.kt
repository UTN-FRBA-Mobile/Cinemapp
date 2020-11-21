package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pago.*

class Pago : AppCompatActivity() {
    private var contadorCaracteres = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

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