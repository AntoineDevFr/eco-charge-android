package com.example.eco_charge_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(private var stations: ArrayList<Station>) : RecyclerView.Adapter<StationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_station_layout, parent, false)
        return StationViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = stations[position]
        // à  faire
        holder.txvName.text = station.nOperateur
        /*holder.txvTitle.text = book.title
        holder.txvAuthor.text = book.author
        holder.txvDate.text = book.date*/
    }

    override fun getItemCount(): Int {
        return stations.size
    }

}