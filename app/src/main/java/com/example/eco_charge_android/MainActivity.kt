package com.example.eco_charge_android

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val SERVER_BASE_URL = "https://localhost:3000/stations"

class MainActivity : AppCompatActivity(), StationCreator {

    private val stationShelf = StationShelf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        displayStationListFragment()
    }

    private fun initData() {
        stationShelf.addStation(
            Station(
                "Toyota",
                "Engie",
                "12345678",
                "1",
                "3 Rue du ketchup",
                12345,
                2,
                12000.0,
                "Type Prise 1",
                "Accès Recharge 1",
                "Accessibilité possible",
                GeoPointBorne(0.0,0.0),
                "13016",
                "PACA",
                "Bouches-du-Rhône"
            )
        )
        stationShelf.addStation(Station("Ferrari","EDF","12345679","2","5 Rue du ketchup",12346,6,16000.0,"Type Prise 2","Accès Recharge 2","Accessibilité impossible",GeoPointBorne(10.0,0.0),"13120","PACA","Bouches-du-Rhône"))
        stationShelf.addStation(Station("Renault","Free","12345680","3","13 Rue du ketchup",12347,10,21500.0,"Type Prise 3","Accès Recharge 3","Accessibilité possible",GeoPointBorne(10.0,50.0),"13008","PACA","Bouches-du-Rhône"))
    }

    private fun displayStationListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentListe = FragmentListe.newInstance(stationShelf.getAllStations())
        fragmentTransaction.replace(R.id.a_fragment_layout, fragmentListe)
        fragmentTransaction.commit()
        //btnCreateBook.visibility = View.VISIBLE
    }

    override fun onStationCreated(station: Station) {

    }
}