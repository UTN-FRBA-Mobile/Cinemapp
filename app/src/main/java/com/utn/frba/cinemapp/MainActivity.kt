package com.utn.frba.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.utn.frba.cinemapp.presentation.ui.LoginActivity
import com.utn.frba.cinemapp.presentation.ui.ScanActivity
import com.utn.frba.cinemapp.presentation.ui.popularMovies.MoviesActivity
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

        optionScan.setOnClickListener {
            val scanIntent = Intent(this, ScanActivity::class.java)
            startActivity(scanIntent)
        }
    }
}