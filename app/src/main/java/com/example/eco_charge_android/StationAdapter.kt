package com.example.eco_charge_android

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(
    private var stations: ArrayList<Station>,
    private val onItemClick: (Station) -> Unit
) : RecyclerView.Adapter<StationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_station_layout, parent, false)
        return StationViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = stations[position]

        // à  faire
        holder.txvName.text = station.n_operateur
        holder.txvAdresse.text = station.ad_station
        val info = station.accessibilite + ", " + station.acces_recharge
        holder.txvInfo.text = info
        //upholder.txvFavorite.setImageDrawable()

        holder.itemView.setOnClickListener {
            onItemClick(station) // Appelle la fonction onItemClick avec la station cliquée
        }

    }

    override fun getItemCount(): Int {
        return stations.size
    }

}