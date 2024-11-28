package com.example.eco_charge_android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://eco-charge.cleverapps.io"

class MainActivity : AppCompatActivity(), StationCreator {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    private val stationService = retrofit.create(StationService::class.java)
    private val stationShelf = StationShelf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stationService.getAllStations().enqueue(object : Callback<List<Station>> {
            override fun onResponse(
                call: Call<List<Station>>,
                response: Response<List<Station>>
            ) {
                val allStations: List<Station>? = response.body()
                if (!allStations.isNullOrEmpty()) {
                    for(b:Station in allStations) {
                        stationShelf.addStation(b)
                    }
                    Log.e("debug","Liste de stations récupérée")
                    Toast.makeText(baseContext, "Liste de stations récupérée", Toast.LENGTH_SHORT).show()

                    displayStationListFragment()
                }
                else if (allStations !== null  && allStations.isEmpty()) {
                    Log.e("debug","Liste de stations récupérée vide")
                    Toast.makeText(baseContext, "Liste de stations récupérée vide", Toast.LENGTH_SHORT).show()

                }
                else {
                    Log.e("debug","Liste de stations récupérée null")
                    Toast.makeText(baseContext, "Liste de stations récupérée null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                Log.e("debug","Erreur dans la récupération des stations")
                Toast.makeText(baseContext, "Erreur dans la récupération des stations", Toast.LENGTH_SHORT).show()
            }
        })

        //initData()
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
        Log.e("debug","Station : ${stationShelf.getAll()}")
        //btnCreateBook.visibility = View.VISIBLE
    }

    override fun onStationCreated(station: Station) {

    }
}