package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utn.frba.cinemapp.models.compra
import com.utn.frba.cinemapp.presentation.ui.LoginActivity
import com.utn.frba.cinemapp.presentation.ui.ScanActivity
import com.utn.frba.cinemapp.presentation.ui.tickets.TicketActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var compraTicket: compra
    lateinit var mail: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Si estoy registrado, obtengo el email
        val bundle: Bundle? = intent.extras;

        if(bundle != null){
            compraTicket = bundle.getSerializable("compra") as compra
            mail = compraTicket.email
        }
        else{
            mail = ""
        }
        // Configura los eventos de los botones
        setupButton(mail?: "");
    }

    override fun onBackPressed() {
        finishAffinity();
    }

    private fun setupButton(email: String){
        //Si el email tiene datos, los muestro en el label
        if(email.isNotEmpty()){
            mainTextUser.setText( getString(R.string.app_welcome ,  email)   )
        }

        //Configura botón de Login
        optionLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        optionMovies.setOnClickListener {
            val moviesIntent = Intent(this, TicketActivity::class.java)
            startActivity(moviesIntent)
        }

        //Configura botón de Registro
        optionRegistration.setOnClickListener {
            val registerIntent = Intent(this, Register::class.java).apply {
            }
            startActivity(registerIntent);
        }

        optionQr.setOnClickListener {
            val scanIntent = Intent(this, ScanActivity::class.java)
            startActivity(scanIntent)
        }

    }
}