package com.example.eco_charge_android

// Définition de la classe StationShelf qui gère le stockage des stations de charge
class StationShelf {
    // Variable mutable pour stocker les stations, avec leur identifiant comme clé
    private var storage = mutableMapOf<String, Station>()
    private val storageBool = ArrayList<String>()

    /**
     * Méthode pour ajouter une station au stockage
     */
    fun addStation(station: Station) {
        storage[station.id_station] = station
        storageBool.add(station.favorite.toString())
    }

    /**
     * Méthode pour récupérer une liste unique des états (favori ou non) de toutes les stations
     */
    fun getBoolStation(): ArrayList<String> {
        return ArrayList(storageBool.toSet())
    }

    /**
     * Méthode pour récupérer toutes les stations, triées par leur numéro de station
     */
    fun getAllStations(): ArrayList<Station> {
        return ArrayList(storage.values
            .sortedBy { station -> station.n_station })
    }

    /**
     * Méthode pour mettre à jour le stockage avec une nouvelle collection de stations
     */
    fun updateStorage(stations: MutableMap<String, Station>) {
        storage = stations
    }
}
