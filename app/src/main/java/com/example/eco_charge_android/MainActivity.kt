package com.example.eco_charge_android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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