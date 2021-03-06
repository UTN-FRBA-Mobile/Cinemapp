package com.utn.frba.cinemapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.utn.frba.cinemapp.models.compra
import com.utn.frba.cinemapp.presentation.ui.LoginActivity
import com.utn.frba.cinemapp.presentation.ui.ScanActivity
import com.utn.frba.cinemapp.presentation.ui.popularMovies.MoviesActivity
import com.utn.frba.cinemapp.presentation.ui.tickets.TicketActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var compraTicket: compra
    lateinit var mail: String
    var idUsuario: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Si estoy registrado, obtengo el email
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            compraTicket = bundle.getSerializable("compra") as compra
            mail = compraTicket.email.toString()
            idUsuario = compraTicket.idUsuario.toString()

        } else {
            mail = ""
            idUsuario = ""
        }
        // Configura los eventos de los botones
        setupButton(mail ?: "", idUsuario ?: "");
    }

    override fun onBackPressed() {
        finishAffinity();
    }

    private fun setupButton(email: String, idUsuario: String) {
        val prefs =
            getSharedPreferences("userToken", Context.MODE_PRIVATE)
        val name =
            prefs.getString("userToken", "")
        Log.v("User Token", name!!)

        //Si el email tiene datos, los muestro en el label
        if (email.isNotEmpty()) {
            mainTextUser.setText(getString(R.string.app_welcome, email))
        }

        //Configura botón de Login
        optionLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        optionMovies.setOnClickListener {
            val moviesIntent = Intent(this, MoviesActivity::class.java)
            startActivity(moviesIntent)
        }

        //Configura botón de Registro
        optionRegistration.setOnClickListener {
            val registerIntent = Intent(this, Register::class.java).apply {
            }
            startActivity(registerIntent);
        }

        optionQr.setOnClickListener {
            if (getUserToken() !== ""){
                val scanIntent = Intent(this, ScanActivity::class.java)
                startActivity(scanIntent)
            }else{
                mostrarAlerta("Necesita estar logueado para usar el QR")
            }
        }

        optionTickets.setOnClickListener {

            if (this.idUsuario == "") {
                Log.i("idUsuario", idUsuario)
                startActivity(Intent(this, LoginActivity::class.java))
                return@setOnClickListener
            }

            val activity = Intent(this, TicketActivity::class.java)
            activity.putExtra("token", UUID.fromString(this.idUsuario))
            startActivity(activity)
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

    private fun getUserToken() :String?{
        val prefs =
            getSharedPreferences("userToken", Context.MODE_PRIVATE)
        return prefs.getString("userToken", "")
    }
}