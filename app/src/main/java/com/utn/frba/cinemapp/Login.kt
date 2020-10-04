package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //configura los botones
        configButton();
    }

    private fun configButton(){
        //Funcionalidad botón cancelar
        loginCancelButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(homeIntent);
        }

        //Funcionalidad botón de login
        loginButton.setOnClickListener {
            if(loginEmailAddress.text.isNotEmpty() && loginPassword.text.isNotEmpty() ){
//                if( loginEmailAddress.text.equals("admin")  && loginPassword.text.equals("123456") ){
                if( loginEmailAddress.text.toString() == "admin"  && loginPassword.text.toString() == "123456" ){
                    val homeIntent = Intent(this, MainActivity::class.java).apply {
                        putExtra("email", "admin")
                    }
                    startActivity(homeIntent);

                }
                else{
                    mostrarAlerta("El usuario o password son incorrectos");
                }

            }
            else{
                mostrarAlerta("El email y el password no pueden ser vacíos");
            }
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

    private fun pantallaPrincipal(emai: String){}
}