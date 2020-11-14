package com.utn.frba.cinemapp.interfaces

import com.utn.frba.cinemapp.models.cine
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CinesApi {
//    @GET("api/v1/cinemas/closest/{latitude}&{longitude}")
    @GET("api/v1/cinemas/closest")
    fun getCinesByProximity(@Query("latitude") latitude: String,
                            @Query("longitude") longitude: String ) : Call<List<cine>>

//    https://utn-2020-2c-desa-mobile.herokuapp.com/api/v1/cinemas/closest?latitude=-34.604040&longitude=-58.411060
}