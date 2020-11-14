package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Si estoy registrado, obtengo el email
        val bundle: Bundle? = intent.extras;
        val mail = bundle?.getString("email");
        // Configura los eventos de los botones
        setupButton(mail ?: "");
//        setupButton();
    }

    private fun setupButton(email: String){
        //Si el email tiene datos, los muestro en el label
        if(email.isNotEmpty()){
//            mainTextUser.text = R.string.app_welcome + " " +  email;
            mainTextUser.setText( getString(R.string.app_welcome ,  email)   )
        }

        //Configura botón de Login
        optionLogin.setOnClickListener {
            val loginIntent = Intent(this, Login::class.java).apply {
            }
            startActivity(loginIntent);
        }

        //Configura botón de Registro
        optionRegistration.setOnClickListener {
            val registerIntent = Intent(this, Register::class.java).apply {
            }
            startActivity(registerIntent);
        }





        //BORRAR ES UNA PRUEBA PARA LA UBICACION
        titulo.setOnClickListener{
            val pruebaLocationIntent = Intent(this, Select_cinema::class.java).apply {
            }
            startActivity(pruebaLocationIntent);
        }





    }
}