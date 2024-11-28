package com.example.eco_charge_android

import java.io.Serializable

data class Station(
    val n_operateur: String,
    val n_enseigne: String,
    val id_station: String,
    val n_station: String,
    val ad_station: String,
    val code_insee: Int,
    val nbre_pdc: Int,
    val puiss_max: Double,
    val type_prise: String,
    val acces_recharge: String,
    val accessibilite: String,
    val geo_point_borne: GeoPointBorne,
    val code_insee_commune: String,
    val region: String,
    val departement: String
): Serializable

