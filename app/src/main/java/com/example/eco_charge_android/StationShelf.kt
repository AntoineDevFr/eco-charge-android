package com.example.eco_charge_android

class StationShelf {
    private val storage = mutableMapOf<String, Station>()

    fun addStation(station: Station) {
        storage[station.idStation] = station
    }

    fun getStation(idStation: String): Station {
        return storage[idStation] ?: throw Exception("Station not found")
    }

    fun getAllStations(): ArrayList<Station> {
        return ArrayList(storage.values
            .sortedBy { station -> station.nStation })
    }

    fun getStationsByRegion(region: String): List<Station> {
        return storage.values.filter { it.region == region }.sortedBy { it.nStation }
    }

    fun getStationsByDepartement(departement: String): List<Station> {
        return storage.values.filter { it.departement == departement }.sortedBy { it.nStation }
    }

    fun getStationsByOperator(operator: String): List<Station> {
        return storage.values.filter { it.nOperateur == operator }.sortedBy { it.nStation }
    }

    fun getTotalNumberOfStations(): Int {
        return storage.size
    }

    fun getStationsWithPowerAbove(minPower: Double): List<Station> {
        return storage.values.filter { it.puissMax > minPower }.sortedByDescending { it.puissMax }
    }

    fun getAccessibleStations(): List<Station> {
        return storage.values.filter { it.accessibilite == "public" }.sortedBy { it.nStation }
    }
}
