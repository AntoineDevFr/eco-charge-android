package com.example.eco_charge_android

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    // faire les findViewbyId
    val txvName = rootView.findViewById<TextView>(R.id.r_station_txv_name)
    /*val nEnseigne: String,
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
    val departement: String*/
}