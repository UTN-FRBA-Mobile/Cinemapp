package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utn.frba.cinemapp.presentation.ui.LoginActivity
import com.utn.frba.cinemapp.presentation.ui.MoviesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtons()
    }

    private fun setupButtons() {

        optionLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        optionMovies.setOnClickListener {
            val moviesIntent = Intent(this, MoviesActivity::class.java)
            startActivity(moviesIntent)
        }
    }
}