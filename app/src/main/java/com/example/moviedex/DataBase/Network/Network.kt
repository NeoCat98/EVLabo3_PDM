package com.example.moviedex.DataBase.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.Search
import retrofit2.http.Query

const val BASE_URL = "http://www.omdbapi.com/?"
interface Network {

    @GET("/")
    fun search(@Query("s")search:String, @Query("apikey") apikey:String): Deferred<Response<Search>>

    @GET("/")
    fun getmovie(@Query("t")search:String, @Query("apikey") apikey:String): Deferred<Response<Movie>>

    companion object {
        var INSTANCE: Network? = null

        fun getNetworkInstance() : Network{
            if(INSTANCE != null) return INSTANCE!!
            else{
                INSTANCE = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(Network::class.java)
                return INSTANCE!!
            }
        }

    }

}