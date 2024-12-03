package com.example.eco_charge_android

import com.google.android.gms.maps.model.LatLng

class StationShelf {
    private val storage = mutableMapOf<String, Station>()

    fun getSize(): Int {
        return storage.size
    }

    fun addStation(station: Station) {
        storage[station.id_station] = station
    }

    fun getAverageLatLng(): LatLng {
        var lat = 0.0
        var long = 0.0
        var i = 0
        for(s:Station in getAllStations()) {
            lat+=s.geo_point_borne.lat
            long+=s.geo_point_borne.lon
            i++
        }
        return LatLng(lat/i,long/i)
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
