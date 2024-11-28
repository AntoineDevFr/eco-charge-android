package com.example.eco_charge_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StationService {
    @GET("stations")
    fun getAllStations(): Call<List<Station>>

}