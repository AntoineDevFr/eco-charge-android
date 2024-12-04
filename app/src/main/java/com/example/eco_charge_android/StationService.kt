package com.example.eco_charge_android



import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface StationService {
    @GET("stations")
    fun getAllStations(): Call<List<Station>>

    @GET("favorites")
    fun getFavorites(): Call<List<Station>>

    @PUT("favorites/:id")
    fun toggleFavorite(id: String, isFavorite: Boolean)

}