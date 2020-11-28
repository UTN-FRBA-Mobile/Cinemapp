package com.utn.frba.cinemapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.utn.frba.cinemapp.MainActivity
import com.utn.frba.cinemapp.R
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.utn.frba.cinemapp.Pago
import com.utn.frba.cinemapp.interfaces.CinesApi
import com.utn.frba.cinemapp.models.cine
import com.utn.frba.cinemapp.models.compra
import com.utn.frba.cinemapp.models.login
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        retrofit = Retrofit.Builder()
            .baseUrl("https://utn-2020-2c-desa-mobile.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        configButtons()
    }

    override fun onBackPressed() {
        mostrarAlerta("Debe apretar el botón de Cancelar")
    }

    private fun configButtons() {
        //Botón de cancelar
        loginCancelButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }

        //Funcionalidad botón de login
        loginButton.setOnClickListener {
            if(loginEmailAddress.text.isNotEmpty() && loginPassword.text.isNotEmpty() ){
                val usuarioALoguearse = login(
                    user = loginEmailAddress.text.toString(),
                    password = loginPassword.text.toString(),
                    token = null
                )
                validarUsuario(usuarioALoguearse)
            }
            else{
                mostrarAlerta("El usuario o password son pueden estar vacíos");
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

    private fun validarUsuario(usuario: login){
        var nombreUsuario: String = usuario.user.toString()

        val servicioApi = retrofit.create<CinesApi>(CinesApi::class.java)
        servicioApi.Loguearse(usuario).enqueue(object : Callback<login> {

            override fun onFailure(call: Call<login>, t: Throwable) {
                mostrarAlerta("Perdón, pero se produjo un error en el logueo");
            }

            override fun onResponse(call: Call<login>?, response: Response<login>?) {
                try{
                    onResult(response?.body(), nombreUsuario)
                }
                catch (e: Exception){
                    mostrarAlerta("Perdón, pero se produjo con los datos retornados.");
                }
            }

        })

    }

    private fun onResult(logueado: login?, nombreUsuario: String){
        if(logueado != null){
            val compraTicket: compra
            val retornoLogin: Intent

            val bundle: Bundle? = intent.extras;

            if(bundle != null){
                compraTicket = bundle.getSerializable("compra") as compra
                compraTicket.idUsuario = logueado.token.toString()
                compraTicket.email = nombreUsuario
                retornoLogin = Intent(this, Pago::class.java).apply {
                }
            }
            else{
                compraTicket = compra(idUsuario = logueado.token.toString(), email = nombreUsuario)
                retornoLogin = Intent(this, MainActivity::class.java).apply {
                }
            }

            retornoLogin.putExtra("compra",compraTicket)
            startActivity(retornoLogin);

        }
        else{
            mostrarAlerta("Error en el usuario o contraseña");
        }

    }
}