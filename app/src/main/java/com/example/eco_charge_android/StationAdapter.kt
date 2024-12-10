package com.example.eco_charge_android

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
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
        holder.txvName.text = station.n_operateur
        holder.txvAdresse.text = station.ad_station

        //Fav
        val drawableRes = if (station.favorite) {
            R.drawable.like
        } else {
            R.drawable.unlike
        }
        holder.txvFavorite.setImageResource(drawableRes)

        val info = station.accessibilite + ", " + station.acces_recharge
        holder.txvInfo.text = info
        if(station.type_prise == "COMBO") {
            holder.txvPrise.setImageResource(R.drawable.t2)
        }
        else if(station.type_prise == "T2-T3-EF" || station.type_prise == "T3-EF-T2" || station.type_prise == "EF-T2-T3") {
            holder.txvPrise.setImageResource(R.drawable.t2_t3_ef)
        }
        else if(station.type_prise == "CHADEMO" || station.type_prise == "Chademo") {
            holder.txvPrise.setImageResource(R.drawable.chademo)
        }
        else if(station.type_prise == "T2-E/F" || station.type_prise == "EF, T2" || station.type_prise == "EF - T2") {
            holder.txvPrise.setImageResource(R.drawable.t2_ef)
        }
        else if(station.type_prise == "T2") {
            holder.txvPrise.setImageResource(R.drawable.t2)
        }
        else if(station.type_prise == "T2P") {
            holder.txvPrise.setImageResource(R.drawable.t2p)
        }
        else {
            holder.txvPrise.setImageResource(R.drawable.ef)
        }

        holder.itemView.setOnClickListener {
            onItemClick(station) // Appelle la fonction onItemClick avec la station cliquée
        }
    }

    override fun getItemCount(): Int {
        return stations.size
    }

}