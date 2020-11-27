package com.utn.frba.cinemapp.interfaces

import com.utn.frba.cinemapp.models.cine
import com.utn.frba.cinemapp.models.login
import com.utn.frba.cinemapp.models.register
import com.utn.frba.cinemapp.models.seat
import retrofit2.Call
import retrofit2.http.*

interface CinesApi {
//    @GET("api/v1/cinemas/closest/{latitude}&{longitude}")
//    https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/cinemas/closest?latitude=-34.604040&longitude=-58.411060
    @GET("api/v1/cinemas/closest")
    fun getCinesByProximity(@Query("latitude") latitude: String,
                            @Query("longitude") longitude: String ) : Call<List<cine>>

    //https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/cinemas/f7cd98e6-a6c5-4b1b-83dd-64fc9beccde8/timetables/15:00:00/places
    @GET("api/v1/cinemas/{idCine}/timetables/{time}/places")
    fun getSeatFromTime(
        @Path("idCine") idCine: String,
        @Path("time") time: String ) : Call<List<seat>>

    @POST("api/v1/users/login")
    fun Loguearse(@Body usuario: login) : Call<login>

    @POST("api/v1/users")
    fun Registrarse(@Body usuario: register) : Call<register>

}