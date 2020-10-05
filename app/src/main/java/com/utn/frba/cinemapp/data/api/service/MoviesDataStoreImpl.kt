package com.utn.frba.cinemapp.data.api.service

import com.utn.frba.cinemapp.config.URL_BACKEND
import com.utn.frba.cinemapp.data.api.entity.MovieListResult
import com.utn.frba.cinemapp.data.api.mappers.MovieDataEntityMapper
import com.utn.frba.cinemapp.data.api.rest.ThemoviedbRestApi
import com.utn.frba.cinemapp.domain.entities.MovieEntity
import com.utn.frba.cinemapp.domain.servicies.MoviesDataStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesDataStoreImpl : MoviesDataStore {

    private val movieDataMapper = MovieDataEntityMapper()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BACKEND)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiRest = retrofit.create<ThemoviedbRestApi>(ThemoviedbRestApi::class.java)

    override fun getPopularMovies(): List<MovieEntity> {
        val call = apiRest.getPopularMovies().enqueue(object : Callback<MovieListResult> {

            override fun onResponse(
                call: Call<MovieListResult>,
                response: Response<MovieListResult>
            ) {
                print("EJECUTANDO")
                if (response.isSuccessful) {
//                    return response.body()?.movies?.map { movieDataMapper.mapFrom(it) }
                    print(response.body())
                }
            }

            override fun onFailure(call: Call<MovieListResult>, t: Throwable) {
                print("SE ROMPIO TODO")
                t.printStackTrace()
            }
        })
//
//        val movieListResult = call.body() as MovieListResult
//        return movieListResult.movies.map { movieDataMapper.mapFrom(it) }
        return ArrayList()
    }
}