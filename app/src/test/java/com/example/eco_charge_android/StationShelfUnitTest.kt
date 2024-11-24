package com.example.eco_charge_android

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StationShelfUnitTest {
    private val stationAlpha = Station(
        nAmenageur = "Amenageur1",
        nOperateur = "Operateur1",
        nEnseigne = "Enseigne1",
        idStation = "ST001",
        nStation = "Station Alpha",
        adStation = "Adresse 1",
        codeInsee = 12345,
        xLongitude = 2.3522,
        yLatitude = 48.8566,
        nbrePdc = 10,
        idPdc = "PDC001",
        puissMax = 50.0,
        typePrise = "Type 2",
        accesRecharge = "Libre",
        accessibilite = "public",
        observations = null,
        dateMaj = "2024-01-01",
        source = "Source1",
        geoPointBorne = GeoPointBorne(lon = 2.3522, lat = 48.8566),
        codeInseeCommune = "75056",
        region = "Île-de-France",
        departement = "Paris"
    )

    private val stationBeta = Station(
        nAmenageur = "Amenageur2",
        nOperateur = "Operateur2",
        nEnseigne = "Enseigne2",
        idStation = "ST002",
        nStation = "Station Beta",
        adStation = "Adresse 2",
        codeInsee = 67890,
        xLongitude = 3.4567,
        yLatitude = 47.1234,
        nbrePdc = 5,
        idPdc = "PDC002",
        puissMax = 30.0,
        typePrise = "Type 3",
        accesRecharge = "Libre",
        accessibilite = "public",
        observations = "Test",
        dateMaj = "2024-01-01",
        source = "Source2",
        geoPointBorne = GeoPointBorne(lon = 3.4567, lat = 47.1234),
        codeInseeCommune = "12345",
        region = "Centre-Val de Loire",
        departement = "Loiret"
    )

    private lateinit var stationShelf: StationShelf

    @Before
    fun setup() {
        stationShelf = StationShelf()
    }

    @Test
    fun getStation_returns_stored_station() {
        stationShelf.addStation(stationAlpha)

        assertEquals(stationAlpha, stationShelf.getStation("ST001"))
    }

    @Test
    fun getAllStations_returns_all_stored_stations_ordered_by_name() {
        stationShelf.addStation(stationBeta)
        stationShelf.addStation(stationAlpha)

        assertEquals(
            listOf(stationAlpha, stationBeta),
            stationShelf.getAllStations()
        )
    }

    @Test
    fun getStationsByRegion_returns_all_stations_in_given_region() {
        stationShelf.addStation(stationAlpha)
        stationShelf.addStation(stationBeta)

        assertEquals(
            listOf(stationAlpha),
            stationShelf.getStationsByRegion("Île-de-France")
        )
    }

    @Test
    fun getStationsByDepartement_returns_all_stations_in_given_departement() {
        stationShelf.addStation(stationAlpha)
        stationShelf.addStation(stationBeta)

        assertEquals(
            listOf(stationBeta),
            stationShelf.getStationsByDepartement("Loiret")
        )
    }

    @Test
    fun getStationsByOperator_returns_all_stations_of_given_operator() {
        stationShelf.addStation(stationAlpha)
        stationShelf.addStation(stationBeta)

        assertEquals(
            listOf(stationBeta),
            stationShelf.getStationsByOperator("Operateur2")
        )
    }

    @Test
    fun getTotalNumberOfStations_returns_number_of_stored_stations() {
        stationShelf.addStation(stationAlpha)
        stationShelf.addStation(stationBeta)

        assertEquals(2, stationShelf.getTotalNumberOfStations())
    }

    @Test
    fun should_not_duplicate_a_station_already_stored() {
        stationShelf.addStation(stationAlpha)
        assertEquals(1, stationShelf.getTotalNumberOfStations())

        stationShelf.addStation(stationAlpha)
        assertEquals(1, stationShelf.getTotalNumberOfStations())
    }

    @Test
    fun getStationsWithPowerAbove_returns_stations_with_high_power() {
        stationShelf.addStation(stationAlpha)
        stationShelf.addStation(stationBeta)

        assertEquals(
            listOf(stationAlpha),
            stationShelf.getStationsWithPowerAbove(40.0)
        )
    }

    @Test
    fun getAccessibleStations_returns_all_public_access_stations() {
        stationShelf.addStation(stationAlpha)
        stationShelf.addStation(stationBeta)

        assertEquals(
            listOf(stationAlpha, stationBeta),
            stationShelf.getAccessibleStations()
        )
    }
}
