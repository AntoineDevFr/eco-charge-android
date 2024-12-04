package com.example.eco_charge_android

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailsStationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var station: Station
    private lateinit var n_station: TextView
    private lateinit var n_enseigne: TextView
    private lateinit var id_station: TextView
    private lateinit var n_operateur: TextView
    private lateinit var ad_station: TextView
    private lateinit var code_insee: TextView
    private lateinit var nbre_pdc: TextView
    private lateinit var puiss_max: TextView
    private lateinit var type_prise: TextView
    private lateinit var acces_recharge: TextView
    private lateinit var accessibilite: TextView
    private lateinit var code_insee_commune: TextView
    private lateinit var region: TextView
    private lateinit var departement: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_station)

        val supportMapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.f_map_details, supportMapFragment)
            .commit()
        supportMapFragment.getMapAsync(this)

        station = intent.getSerializableExtra("DATA_KEY") as Station

        n_station = findViewById(R.id.n_station)
        n_enseigne = findViewById(R.id.n_enseigne)
        id_station = findViewById(R.id.id_station)
        n_operateur = findViewById(R.id.n_operateur)
        ad_station = findViewById(R.id.ad_station)
        code_insee = findViewById(R.id.code_insee)
        nbre_pdc = findViewById(R.id.nbre_pdc)
        puiss_max = findViewById(R.id.puiss_max)
        type_prise = findViewById(R.id.type_prise)
        acces_recharge = findViewById(R.id.acces_recharge)
        accessibilite = findViewById(R.id.accessibilite)
        code_insee_commune = findViewById(R.id.code_insee_commune)
        region = findViewById(R.id.region)
        departement = findViewById(R.id.departement)

        n_station.text = station.n_station
        n_enseigne.text = station.n_enseigne
        id_station.text = station.id_station
        n_operateur.text = station.n_operateur
        ad_station.text = station.ad_station
        code_insee.text = station.code_insee?.toString() ?: "N/A"
        nbre_pdc.text = station.nbre_pdc.toString()
        puiss_max.text = "${station.puiss_max} kW"
        type_prise.text = station.type_prise
        acces_recharge.text = station.acces_recharge
        accessibilite.text = station.accessibilite
        code_insee_commune.text = station.code_insee_commune ?: "N/A"
        region.text = station.region ?: "N/A"
        departement.text = station.departement ?: "N/A"




    }

    override fun onMapReady(p0: GoogleMap) {
        val position = LatLng(station.geo_point_borne.lat,station.geo_point_borne.lon)
        p0.addMarker(
            MarkerOptions()
                .position(position)
                .title(station.n_station)
                .snippet("${station.code_insee}, " + station.accessibilite)
        )
        val zoomLevel = 18.0f // Niveau de zoom, entre 2.0 (monde) et 21.0 (rue)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, zoomLevel)
        p0.moveCamera(cameraUpdate)
    }
}