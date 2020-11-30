package com.utn.frba.cinemapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.utn.frba.cinemapp.adaptadores.SelectCinemaAdapter
import com.utn.frba.cinemapp.interfaces.CinesApi
import com.utn.frba.cinemapp.models.cine
import com.utn.frba.cinemapp.models.compra
import kotlinx.android.synthetic.main.activity_select_cinema.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Select_cinema : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var retrofit: Retrofit
    lateinit var compraTicket: compra
    lateinit var posicion: List<String>

    private var PERMISSION_ID = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_cinema)

        compraTicket = intent.getSerializableExtra("compra") as compra
        Log.i("compraTicket", compraTicket.toString())

        /***********************************************/
        //inicializo el fused
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        posicion = getUltimaPosicion();

        retrofit = Retrofit.Builder()
            .baseUrl("https://utn-2020-2c-desa-mobile.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//        // creo el servicio para hacer las llamadas
//        val servicioApi = retrofit.create<CinesApi>(CinesApi::class.java)
//
////        servicioApi.getCinesByProximity("-34.604040", "-58.411060").enqueue(object :
//          servicioApi.getCinesByProximity(posicion[0], posicion[1]).enqueue(object :
//            Callback<List<cine>> {
//            override fun onFailure(call: Call<List<cine>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call<List<cine>>?, response: Response<List<cine>>?) {
//
//                try {
//                    val cines = response?.body()
//                    if (!cines.isNullOrEmpty()) {
//                        initRecycleViewSelectCinema(cines)
//
//                    }
//                    //Log.d("Debug", " Sarasa " + Gson().toJson(cines))
//
//                } catch (e: Exception) {
//                    //Log.i("sarasa", e.ge())
//                }
//
//            }
//
//        })


    }

    private fun obtenerCines(latitud: String, longitud: String){
        // creo el servicio para hacer las llamadas
        val servicioApi = retrofit.create<CinesApi>(CinesApi::class.java)

//        servicioApi.getCinesByProximity("-34.604040", "-58.411060").enqueue(object :
        servicioApi.getCinesByProximity(latitud, longitud).enqueue(object :
            Callback<List<cine>> {
            override fun onFailure(call: Call<List<cine>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<List<cine>>?, response: Response<List<cine>>?) {

                try {
                    val cines = response?.body()
                    if (!cines.isNullOrEmpty()) {
                        initRecycleViewSelectCinema(cines)

                    }
                    //Log.d("Debug", " Sarasa " + Gson().toJson(cines))

                } catch (e: Exception) {
                    //Log.i("sarasa", e.ge())
                }

            }

        })

    }

    /***
     * Obtenemos la última posición
     */
    private fun getUltimaPosicion() : List<String>{
        var valorARetornar: List<String> = emptyList()

        if (checkPermission()) {
            if (estaLaLocacionhabilitada()) {
                //obtenemos la ubicación
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    var location: Location? = it.result
                    if (location == null) {
                        // Si no hay una última ubicación, entonces tengo que tomar una nueva
                        getNuevaUbicacion();
                    } else {
                        Log.d(
                            "Debug",
                            "la latitud es" + location.latitude + " y la long " + location.longitude
                        )
                        valorARetornar = listOf(location.latitude.toString(), location.longitude.toString())
                        obtenerCines(location.latitude.toString(), location.longitude.toString())
                    }
                }
            } else {
                Toast.makeText(this, "Habilite el servicio de ubicación", Toast.LENGTH_SHORT)
                    .show();
            }

        } else {
            pedirPermisos();
        }
        return valorARetornar

    }

    /***
     * Obtiene la nueva ubicación
     */
    private fun getNuevaUbicacion() {
        locationRequest = LocationRequest();
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY;
        locationRequest.interval = 0;
        locationRequest.fastestInterval = 0;
        locationRequest.numUpdates = 2;

        if (checkPermission()) {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        }

    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            //super.onLocationResult(p0)
            var lastLocation = p0?.lastLocation;
            Log.d(
                "Debug",
                "la locacion es lat " + lastLocation?.latitude + " la long es " + lastLocation?.longitude
            )

        }
    }


    // Verifico si el usuario tiene los permisos
    private fun checkPermission(): Boolean {
        if (checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            ||
            checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true;

        }
        return false;
    }

    /***
     * Nos permite pedir los permisos del usuario
     */
    private fun pedirPermisos() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    /***
     * verifica si el servicio de location está habilitado
     */
    private fun estaLaLocacionhabilitada(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // verifico el resultado de los permisos
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "SI, ya tenés los permisos");
            }
        }

    }

    fun initRecycleViewSelectCinema(cines: List<cine>) {
        recycleViewSelectCinema.layoutManager = LinearLayoutManager(this)
        val adapter = SelectCinemaAdapter(cines, compraTicket)
        recycleViewSelectCinema.adapter = adapter
    }

}