package com.example.eco_charge_android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Définition de la classe StationViewHolder qui représente un élément de la liste dans un RecyclerView
 * Cette classe est utilisée pour lier les vues de chaque item du RecyclerView aux données correspondantes
 */
class StationViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    val txvName = rootView.findViewById<TextView>(R.id.r_station_txv_name)
    val txvAdresse = rootView.findViewById<TextView>(R.id.r_station_txv_adresse)
    val txvInfo = rootView.findViewById<TextView>(R.id.r_station_txv_info)
    val txvFavorite = rootView.findViewById<ImageView>(R.id.r_image_like)
    val txvPrise = rootView.findViewById<ImageView>(R.id.imageView2)
}