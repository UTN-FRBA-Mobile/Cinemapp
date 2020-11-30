package com.utn.frba.cinemapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.utn.frba.cinemapp.interfaces.CinesApi
import com.utn.frba.cinemapp.models.cine
import com.utn.frba.cinemapp.models.compra
import com.utn.frba.cinemapp.models.seat
import com.utn.frba.cinemapp.presentation.ui.ConfirmacionActivity
import com.utn.frba.cinemapp.presentation.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_select_seat_time.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Select_seat_time : AppCompatActivity() {
    val RESERVADO: Int = 1
    val LIBRE: Int = 0
    lateinit var retrofit: Retrofit
    lateinit var servicioApi: CinesApi
    lateinit var compraTicket: compra
    lateinit var horario: String
    var seats: MutableList<Int> = mutableListOf()
    var diaSeleccionado: String = ""
    var horaSeleccionada: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat_time)

        configurarBotones()

        //Obtengo los datos que vengo arrastrando con la compra
        val bundle: Bundle? = intent.extras;
        compraTicket = bundle?.getSerializable("compra") as compra
//        Toast.makeText(this, "la compra es: ${compraTicket.idCine}", Toast.LENGTH_LONG).show()

        //Servicio para consumir del backend
        retrofit = Retrofit.Builder()
            .baseUrl("https://utn-2020-2c-desa-mobile.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // creo el servicio para hacer las llamadas
        servicioApi = retrofit.create<CinesApi>(CinesApi::class.java)

        //cargo los días que tiene ese cine
        setupDaysCinema()


        //configuro los eventlistener de los objetos de la pantalla
        //setupElements()

        // Obtengo del backend la estructura del cine seleccionado
        // Ahora armo el layout
        //setScreen();
    }

    private fun setupDaysCinema(){
        servicioApi.getDay(compraTicket.idCine.toString(),compraTicket.idPelicula.toString()).enqueue(object : Callback<List<String>> {

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                mostrarAlerta("Error al obtener las fechas")
            }

            override fun onResponse(call: Call<List<String>>?, response: Response<List<String>>?) {
                try{
                    val fecha = response?.body()
                    if(fecha != null){
                        onResultFecha(fecha)
                    }

//                    Log.d("Debug", " Sarasa " + Gson().toJson(asientos))
                }
                catch (e: Exception){
                    //Log.i("sarasa", e.ge())
                }
            }
        })
    }

    private fun onResultFecha(fechas: List<String>){
        dateLinearLayoutY.removeAllViewsInLayout()

        for(fecha in fechas){
            var fechaNueva: TextView = TextView(this)
//            var layoutParametros: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50,50)
//            layoutParametros.setMargins(30, 30, 30, 30)
//            fechaNueva.setLayoutParams(layoutParametros)
            fechaNueva.setBackgroundResource(R.drawable.button_transparent)
            fechaNueva.setText(fecha.toString())
            fechaNueva.setTag(fecha.toString())
            fechaNueva.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)

            fechaNueva.setOnClickListener{
                diaSeleccionado = it.getTag().toString()
                cargaHora(it.getTag().toString())
//                if ( it.getTag() == LIBRE ){
//                    it.setBackgroundResource(R.drawable.button_acept)
//                    it.setTag(RESERVADO)
//                    cargaHora(it.)
//                }
//                else{
//                    it.setBackgroundResource(R.drawable.button_transparent)
//                    it.setTag(LIBRE)
//                    seats.remove(it.id.toString())
//                }
//
//                println(seats)

            }

            dateLinearLayoutY.addView(fechaNueva)
        }
//        selecSeatDateSpinner.adapter(ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fechas) )
//        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,lenguajes
    }

    private fun cargaHora(diaElegido: String){
        servicioApi.getTimeFromDate(compraTicket.idCine.toString(),compraTicket.idPelicula.toString(), diaElegido).enqueue(object : Callback<List<String>> {

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                mostrarAlerta("Error al obtener las horas")
            }

            override fun onResponse(call: Call<List<String>>?, response: Response<List<String>>?) {
                try{
                    val hora = response?.body()
                    if(hora != null){
                        onResultHora(hora)
                    }

//                    Log.d("Debug", " Sarasa " + Gson().toJson(asientos))
                }
                catch (e: Exception){
                    //Log.i("sarasa", e.ge())
                }
            }
        })


    }

    private fun onResultHora(horas: List<String>) {
        horaLinearLayoutY.removeAllViewsInLayout()

        for (hora in horas) {
            var horaNueva: TextView = TextView(this)
            horaNueva.setBackgroundResource(R.drawable.button_transparent)
            horaNueva.setText(hora.toString())
            horaNueva.setTag(hora.toString())
            horaNueva.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)

            horaNueva.setOnClickListener {
                horaSeleccionada = it.getTag().toString()
                cargaSalas(it.getTag().toString())
            }

            horaLinearLayoutY.addView(horaNueva)

        }
    }

    private fun cargaSalas(horaElegido: String){
    servicioApi.getSeatFromTime(compraTicket.idCine,compraTicket.idPelicula.toString(),diaSeleccionado,horaSeleccionada,"1") .enqueue(object : Callback<List<String>> {

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            mostrarAlerta("Error al obtener las butacas")
        }

        override fun onResponse(call: Call<List<String>>?, response: Response<List<String>>?) {
            try{
                val asientos = response?.body()
                setScreen(asientos)
                seats.clear()
                Log.d("Debug", " Sarasa " + Gson().toJson(asientos))
            }
            catch (e: Exception){
                //Log.i("sarasa", e.ge())
            }
        }
    })

}

    private fun configurarBotones(){
        var compraTicketconAsientos: compra
        var PagoIntent: Intent

        continueSelectSeatButton.setOnClickListener {
            if(compraTicket.idUsuario.isNullOrEmpty()){
                compraTicketconAsientos = compra(compraTicket.idCine,horaSeleccionada,seats)
                PagoIntent = Intent(this, LoginActivity::class.java).apply { }
            }
            else{
                // TODO agregar el nombre id de usuario
                compraTicketconAsientos = compra(compraTicket.idCine,horaSeleccionada,seats)
                PagoIntent = Intent(this, ConfirmacionActivity::class.java).apply { }
                Log.v("VOY A PAGAR", "s")
            }
//            val PagoIntent = Intent(this, Pago::class.java).apply {
//            }
            PagoIntent.putExtra("compra",compraTicketconAsientos)
            startActivity(PagoIntent);
        }
    }

    // TODO: debería recibir un objeto con el estado y la estructura del cine
//    private fun setScreen(asientos: List<seat>?){
    private fun setScreen(asientos: List<String>?){
        var contador: Int = 1
        val filas: Int
        val columnas: Int


        seatLinearLayoutY.removeAllViewsInLayout()

//        if(!asientos.isNullOrEmpty()){
//            filas = (asientos.size / 2).toInt() - 10 //19 //3
//            columnas = (asientos.size / 2).toInt()  -10 // 19//3
//        }
//        else{
            filas = 3
            columnas = 3
//        }

        for (i in 1..filas) {
            var lienarNuevo: LinearLayout = LinearLayout(this)
            lienarNuevo.setGravity(Gravity.CENTER)

            for(j in 1.. columnas){
                var butacaNueva: TextView = TextView(this)
                //Para pasarle los parámetros de layout al textview, necesito hacerlo a través de un objeto layoutParam
                var layoutParametros: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50,50)
                layoutParametros.setMargins(30, 30, 30, 30)
                butacaNueva.setLayoutParams(layoutParametros)

                // TODO: si está ocupado que ponga un fondo, sino que ponga el otro
                //butacaNueva.setBackgroundResource(R.drawable.button_acept)

//                butacaNueva.set(asientos?.get(contador)?.name);
//                if(asientos?.get(contador)?.status!!){

                if(asientos != null){

                    // if( asientos!![contador].status){
                    if( asientos.contains(contador.toString()) ){
                        butacaNueva.setBackgroundResource(R.drawable.button_transparent)
                        butacaNueva.setTag(LIBRE)
                    }
                    else{
                        butacaNueva.setBackgroundResource(R.drawable.button_acept)
                        butacaNueva.setTag(RESERVADO)
                        //butacaNueva.setTag(LIBRE, asientos.get(contador))
                    }

                }


                butacaNueva.id = contador

                butacaNueva.setOnClickListener{
                    if ( it.getTag() == LIBRE ){
                        it.setBackgroundResource(R.drawable.button_acept)
                        it.setTag(RESERVADO)
                        seats.add(it.id.toInt())
                    }
                    else{
                        it.setBackgroundResource(R.drawable.button_transparent)
                        it.setTag(LIBRE)
                        seats.remove(it.id.toInt())
                    }

                    println(seats)

                }

                lienarNuevo.addView(butacaNueva)
                contador++;

            }
            seatLinearLayoutY.addView(lienarNuevo)
        }

    }

    private fun setupElements(){
        //Configura spinner con horarios

//        selecSeatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                horario = parent?.getItemAtPosition(position).toString()
//                servicioApi.getSeatFromTime(compraTicket.idCine, parent?.getItemAtPosition(position).toString()).enqueue(object : Callback<List<seat>> {
//
//                    override fun onFailure(call: Call<List<seat>>, t: Throwable) {
//                        TODO("Not yet implemented")
//                    }
//
//                    override fun onResponse(call: Call<List<seat>>?, response: Response<List<seat>>?) {
//                        try{
//                            val asientos = response?.body()
//                            setScreen(asientos)
//                            seats.clear()
//                            Log.d("Debug", " Sarasa " + Gson().toJson(asientos))
//                        }
//                        catch (e: Exception){
//                            //Log.i("sarasa", e.ge())
//                        }
//                    }
//                })
//
//            }
//        }
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

private operator fun Adapter.invoke(arrayAdapter: ArrayAdapter<String>) {

}

