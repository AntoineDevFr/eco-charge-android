package com.example.eco_charge_android

import com.google.android.gms.maps.model.LatLng

class StationShelf {
    private val storage = mutableMapOf<String, Station>()
    private val storageBool = ArrayList<String>()

    fun getSize(): Int {
        return storage.size
    }

    fun addStation(station: Station) {
        storage[station.id_station] = station
        storageBool.add(station.favorite.toString())
    }

    fun getBoolStation(): ArrayList<String> {
        return ArrayList(storageBool.toSet())
    }



    fun getStation(idStation: String): Station {
        return storage[idStation] ?: throw Exception("Station not found")
    }

    fun getAllStations(): ArrayList<Station> {
        return ArrayList(storage.values
            .sortedBy { station -> station.n_station })
    }

    fun getStationsByRegion(region: String): List<Station> {
        return storage.values.filter { it.region == region }.sortedBy { it.n_station }
    }

    fun getStationsByDepartement(departement: String): List<Station> {
        return storage.values.filter { it.departement == departement }.sortedBy { it.n_station }
    }

    fun getStationsByOperator(operator: String): List<Station> {
        return storage.values.filter { it.n_operateur == operator }.sortedBy { it.n_station }
    }

    fun getTotalNumberOfStations(): Int {
        return storage.size
    }

    fun getStationsWithPowerAbove(minPower: Double): List<Station> {
        return storage.values.filter { it.puiss_max > minPower }.sortedByDescending { it.puiss_max }
    }

    fun getAccessibleStations(): List<Station> {
        return storage.values.filter { it.accessibilite == "public" }.sortedBy { it.n_station }
    }
}
