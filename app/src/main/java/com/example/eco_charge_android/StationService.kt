package com.example.eco_charge_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface StationService {
    @GET("stations")
    fun getAllStations(): Call<List<Station>>

    @PUT("stations/favorites/{id}")
    fun toggleFavorite(@Path("id") id: String, @Body body: FavoriteRequest): Call<Void>
}