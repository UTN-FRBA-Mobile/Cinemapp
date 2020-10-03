package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //configura los botones
        configButton();
    }

    private fun configButton(){
        loginCancelButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(homeIntent);
        }

    }
}