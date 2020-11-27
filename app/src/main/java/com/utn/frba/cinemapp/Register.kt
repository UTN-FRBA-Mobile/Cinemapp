package com.utn.frba.cinemapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        configurarBotones()
    }

    override fun onBackPressed() {
        mostrarAlerta("Debe apretal el botón de cancelar")
    }

    private fun configurarBotones(){
        registerButtonCancel.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }
    }

    private fun mostrarAlerta( texto: String){
        var builder = AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage(texto);
        builder.setPositiveButton("Aceptar", null);
        var dialog: AlertDialog = builder.create();
        dialog.show();
    }

}