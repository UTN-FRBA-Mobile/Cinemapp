package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configura los eventos de los botones
        setupButton();
    }

    private fun setupButton(){
        //Configura botón de Login
        optionLogin.setOnClickListener {
            val loginIntent = Intent(this, Login::class.java).apply {
            }
            startActivity(loginIntent);
        }
    }
}