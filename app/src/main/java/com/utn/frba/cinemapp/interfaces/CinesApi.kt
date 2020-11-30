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
    @GET("api/v1/cinemas/closest/latitude/{latitude}/longitude/{longitude}")
    fun getCinesByProximity(
        @Path("latitude") latitude: String,
        @Path("longitude") longitude: String
    ): Call<List<cine>>

    //https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/cinemas/f7cd98e6-a6c5-4b1b-83dd-64fc9beccde8/timetables/15:00:00/places
    //{{url}}/api/v1/cinemas/7f55a7f0-10a8-48f2-a4fa-49cc48d0589c/seats/enables/ids?movie_id=622855&movie_date=2020-12-06&movie_time=23:00:00&room=1
    @GET("api/v1/cinemas/{idCine}/seats/enables/ids")
    fun getSeatFromTime(
        @Path("idCine") idCine: String,
        @Query("movie_id") movie_id: String,
        @Query("movie_date") movie_date: String,
        @Query("movie_time") movie_time: String,
        @Query("room") room: String
    ): Call<List<String>>

    @POST("api/v1/users/login")
    fun Loguearse(@Body usuario: login): Call<login>

    @POST("api/v1/users")
    fun Registrarse(@Body usuario: register): Call<register>

//    {{url}}/api/v1/cinemas/7f55a7f0-10a8-48f2-a4fa-49cc48d0589c/dates?movie_id=622855
    @GET("/api/v1/cinemas/{idCine}/dates")
    fun getDay(
        @Path("idCine") idCine: String,
        @Query("movie_id") idMovie: String
    ): Call<List<String>>

    //{{url}}/api/v1/cinemas/7f55a7f0-10a8-48f2-a4fa-49cc48d0589c/times?movie_id=622855&movie_date=2020-12-06
    @GET("/api/v1/cinemas/{idCine}/times")
    fun getTimeFromDate(
        @Path("idCine") idCine: String,
        @Query("movie_id") idMovie: String,
        @Query("movie_date") movie_date: String
    ): Call<List<String>>



}