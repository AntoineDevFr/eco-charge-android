package com.example.eco_charge_android

data class Station(
    val nAmenageur: String,
    val nOperateur: String,
    val nEnseigne: String,
    val idStation: String,
    val nStation: String,
    val adStation: String,
    val codeInsee: Int,
    val xLongitude: Double,
    val yLatitude: Double,
    val nbrePdc: Int,
    val idPdc: String,
    val puissMax: Double,
    val typePrise: String,
    val accesRecharge: String,
    val accessibilite: String,
    val observations: String?,
    val dateMaj: String,
    val source: String,
    val geoPointBorne: GeoPointBorne,
    val codeInseeCommune: String,
    val region: String,
    val departement: String
)

