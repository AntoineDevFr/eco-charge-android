package com.example.eco_charge_android.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.eco_charge_android.R
import com.example.eco_charge_android.model.Station
import com.example.eco_charge_android.service.StationService
import com.example.eco_charge_android.model.StationShelf
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// URL de base pour le serveur utilisé par l'application.
const val SERVER_BASE_URL = "https://eco-charge.cleverapps.io"

/**
 * Activité principale de l'application.
 * Elle gère l'interface utilisateur et la navigation entre les fragments (liste, carte, informations).
 * Implémente [OnMapReadyCallback] pour initialiser et gérer la carte Google Maps.
 */
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    // Fragment de carte pour afficher Google Maps.
    private lateinit var supportMapFragment: SupportMapFragment

    // Configuration Retrofit pour la communication avec l'API REST.
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    // Service pour les opérations liées aux stations de recharge.
    private val stationService = retrofit.create(StationService::class.java)

    // Objet pour stocker et gérer les stations localement.
    private val stationShelf = StationShelf()

    private val btnListe: Button by lazy {
        findViewById(R.id.liste_menu)
    }
    private var boolListe = true

    private val btnMaps: Button by lazy {
        findViewById(R.id.maps_menu)
    }
    private var boolMaps = false

    private val btnInfo: Button by lazy {
        findViewById(R.id.info_menu)
    }
    private var boolInfo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportMapFragment = SupportMapFragment.newInstance()
        supportMapFragment.getMapAsync(this)

        // Configuration de la barre d'outils.
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar3)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)

        // Récupération des données des stations via l'API.
        stationService.getAllStations().enqueue(object : Callback<List<Station>> {
            override fun onResponse(
                call: Call<List<Station>>,
                response: Response<List<Station>>
            ) {
                val allStations: List<Station>? = response.body()
                if (!allStations.isNullOrEmpty()) {
                    for(b: Station in allStations) {
                        stationShelf.addStation(b)
                    }
                    Log.e("debug","Liste de stations récupérée")
                    Toast.makeText(baseContext, "Liste de stations récupérée", Toast.LENGTH_SHORT).show()

                    val res = stationShelf.getBoolStation()
                    for(i:String in res) {
                        Log.e("debug", i)
                    }
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

        // Gestion des clics sur les boutons pour naviguer entre les fragments.
        btnListe.setOnClickListener {
            if (!boolListe) {
                boolListe = true
                boolMaps = false
                boolInfo = false
                displayStationListFragment()
            }
        }
        btnMaps.setOnClickListener {
            if (!boolMaps) {
                boolListe = false
                boolMaps = true
                boolInfo = false
                displayMapFragment()
            }
        }
        btnInfo.setOnClickListener {
            if (!boolInfo) {
                boolListe = false
                boolMaps = false
                boolInfo = true
                displayInfoFragment()
            }
        }
    }

    /**
     * Affiche le fragment de la liste des stations.
     */
    private fun displayStationListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentListe = FragmentListe.newInstance(stationShelf.getAllStations())
        fragmentTransaction.replace(R.id.a_fragment_layout, fragmentListe)
        fragmentTransaction.commit()
    }


    fun updateStationShelf(stations: ArrayList<Station>) {
        val stationsMap = stations.associateBy { it.id_station }.toMutableMap()
        stationShelf.updateStorage(stationsMap)
    }

    /**
     * Affiche le fragment de la carte Google Maps.
     */
    private fun displayMapFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.a_fragment_layout, supportMapFragment)
        fragmentTransaction.commit()
        supportMapFragment.getMapAsync(this)
    }

    private fun displayInfoFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentInfo = FragmentInfo.newInstance(stationShelf.getAllStations())
        fragmentTransaction.replace(R.id.a_fragment_layout, fragmentInfo)
        fragmentTransaction.commit()
    }

    /**
     * Affiche le fragment d'informations supplémentaires.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Rafraichir la liste de données
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                stationService.getAllStations().enqueue(object : Callback<List<Station>> {
                    override fun onResponse(
                        call: Call<List<Station>>,
                        response: Response<List<Station>>
                    ) {
                        val allStations: List<Station>? = response.body()
                        if (!allStations.isNullOrEmpty()) {
                            for(b: Station in allStations) {
                                stationShelf.addStation(b)
                            }
                            Log.e("debug","Liste de stations récupérée")
                            Toast.makeText(baseContext, "Liste de stations récupérée", Toast.LENGTH_SHORT).show()

                            val res = stationShelf.getBoolStation()
                            for(i:String in res) {
                                Log.e("debug", i)
                            }
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
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
     * Callback appelé lorsque la carte est prête. Ajoute des marqueurs pour chaque station.
     */
    override fun onMapReady(mMap: GoogleMap) {
        for(s: Station in stationShelf.getAllStations()) {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(s.geo_point_borne.lat,s.geo_point_borne.lon))
                    .title(s.n_station)
                    .snippet("${s.code_insee}, " + s.accessibilite)
            )
        }
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5F))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(45.8475,2.5447)))
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setPadding(0,0,0,200)
    }
}