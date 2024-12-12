package com.example.eco_charge_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eco_charge_android.R
import com.example.eco_charge_android.model.Station

/**
 * Adaptateur pour gérer l'affichage des stations dans une RecyclerView.
 * @param stations Liste des stations à afficher.
 * @param onItemClick Callback pour gérer les clics sur les éléments de la liste.
 */
class StationAdapter(
    private var stations: ArrayList<Station>,
    private val onItemClick: (Station) -> Unit
) : RecyclerView.Adapter<StationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_station_layout, parent, false)
        return StationViewHolder(rowView)
    }

    /**
     * Lie les données d'une station à un ViewHolder.
     */
    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = stations[position]
        holder.txvName.text = station.n_station
        holder.txvAdresse.text = station.ad_station

        // Met à jour l'icône de favori.
        val drawableRes = if (station.favorite) {
            R.drawable.like
        } else {
            R.drawable.unlike
        }
        holder.txvFavorite.setImageResource(drawableRes)

        val info = station.accessibilite + ", " + station.acces_recharge
        holder.txvInfo.text = info

        // Configure l'image en fonction du type de prise.
        val typePriseRes = when (station.type_prise) {
            "COMBO" -> R.drawable.t2
            "T2-T3-EF", "T3-EF-T2", "EF-T2-T3" -> R.drawable.t2_t3_ef
            "CHADEMO", "Chademo" -> R.drawable.chademo
            "T2-E/F", "EF, T2", "EF - T2" -> R.drawable.t2_ef
            "T2" -> R.drawable.t2
            "T2P" -> R.drawable.t2p
            else -> R.drawable.ef
        }
        holder.txvPrise.setImageResource(typePriseRes)

        // Gère les clics sur l'élément de la liste.
        holder.itemView.setOnClickListener {
            onItemClick(station) // Appelle la fonction onItemClick avec la station cliquée
        }
    }

    /**
     * Retourne le nombre total d'éléments dans la liste.
     */
    override fun getItemCount(): Int {
        return stations.size
    }

    /**
     * Met à jour la liste des stations et rafraîchit l'affichage.
     * @param newList Nouvelle liste des stations.
     */
    fun updateList(newList: ArrayList<Station>) {
        stations = newList
        notifyDataSetChanged()
    }

}