package com.example.eco_charge_android

data class Station(
    val nOperateur: String,
    val nEnseigne: String,
    val idStation: String,
    val nStation: String,
    val adStation: String,
    val codeInsee: Int,
    val nbrePdc: Int,
    val puissMax: Double,
    val typePrise: String,
    val accesRecharge: String,
    val accessibilite: String,
    val geoPointBorne: GeoPointBorne,
    val codeInseeCommune: String,
    val region: String,
    val departement: String
)

