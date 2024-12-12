package com.example.eco_charge_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

// Définition de l'interface qui expose les services pour l'API des stations de charge
interface StationService {
    // Méthode pour récupérer toutes les stations de charge
    @GET("stations")
    fun getAllStations(): Call<List<Station>>

    // Méthode pour basculer un favori d'une station de charge en fonction de son identifiant
    @PUT("stations/favorites/{id}")
    fun toggleFavorite(@Path("id") id: String, @Body body: FavoriteRequest): Call<Void>
}