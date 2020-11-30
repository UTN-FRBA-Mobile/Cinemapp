package com.utn.frba.cinemapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.utn.frba.cinemapp.interfaces.CinesApi
import com.utn.frba.cinemapp.models.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Register : AppCompatActivity() {
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build();

        retrofit = Retrofit.Builder().baseUrl("https://utn-2020-2c-desa-mobile.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        configurarBotones()
    }

    override fun onBackPressed() {
        mostrarAlerta("Debe apretar el botón de cancelar")
    }

    private fun configurarBotones(){
        //Botón de cancelar
        registerButtonCancel.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }

        //Botón para hacer la registración
        registerButtonLogin.setOnClickListener {
            if(registerName.text.isNotEmpty() && registerLastName.text.isNotEmpty() && registerEmailAddress.text.isNotEmpty() && registerPassword.text.isNotEmpty() ){
                val usuarioALoguearse = usuario(
                    user = registerEmailAddress.text.toString(),
                    password = registerPassword.text.toString()
                )
                val tarjetaDeCredito: List<CreditCard> = emptyList()//listOf( CreditCard(number = "1111-1111-1111-1111", name = "sarasa1", expiration = "01/2020") )

                val registracion = register(login = usuarioALoguearse, creditCars = tarjetaDeCredito)

                crearUsuario(registracion)
            }
            else{
                mostrarAlerta("Todos los campos anteriores son obligatorios.");
            }
        }
    }

    private fun crearUsuario(registracion: register){
        val nombreUsuario: String = registracion.login?.user.toString()

        val servicioApi = retrofit.create<CinesApi>(CinesApi::class.java)
        servicioApi.Registrarse(registracion).enqueue(object : Callback<respuestaRegistro> {

            override fun onFailure(call: Call<respuestaRegistro>, t: Throwable) {
                mostrarAlerta("Perdón, pero se produjo un error al crear el usuario");
            }

            override fun onResponse(call: Call<respuestaRegistro>?, response: Response<respuestaRegistro>?) {
                try{
                    onResult(response?.body())
                }
                catch (e: Exception){
                    mostrarAlerta("Perdón, pero se produjo con los datos retornados.");
                }
            }
        })
    }

    private fun onResult(registracion: respuestaRegistro?){
        if(registracion != null){
            if(registracion.idUsuario.isNullOrEmpty()){
                val builder = AlertDialog.Builder(this)
                    .setTitle("Creación correcta")
                    .setMessage("Vuelva a la pantalla principal y haga el logueo")

                builder.setPositiveButton("Si"){ dialogInterface, which ->
                    val homeIntent = Intent(this, MainActivity::class.java)
                    startActivity(homeIntent)
                }
            }
            else{
                mostrarAlerta("Error al crear el usuario");
            }
        }
        else{
            mostrarAlerta("Error al crear el usuario");
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