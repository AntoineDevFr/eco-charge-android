package com.example.eco_charge_android

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

val UPDATED_STATION = "CREATED_STATION"

class DetailsStationActivity : AppCompatActivity(), OnMapReadyCallback {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    private val stationService = retrofit.create(StationService::class.java)

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
    private lateinit var imageview: ImageView
    private lateinit var btnAddFav: Button
    private lateinit var imageLike: ImageView
    private lateinit var backButton: ImageView

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
        imageview = findViewById(R.id.imageView)
        btnAddFav = findViewById(R.id.bt_ajouter_fav)
        imageLike = findViewById(R.id.like_image)
        backButton = findViewById(R.id.retour)

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

        val drawableRes = if (station.favorite) {
            R.drawable.like
        } else {
            R.drawable.unlike
        }
        imageLike.setImageResource(drawableRes)
        backButton.setImageResource(R.drawable.baseline_arrow_back_24)

        if(station.type_prise == "COMBO") {
            imageview.setImageResource(R.drawable.t2)
        }
        else if(station.type_prise == "T2-T3-EF" || station.type_prise == "T3-EF-T2" || station.type_prise == "EF-T2-T3") {
            imageview.setImageResource(R.drawable.t2_t3_ef)
        }
        else if(station.type_prise == "CHADEMO" || station.type_prise == "Chademo") {
            imageview.setImageResource(R.drawable.chademo)
        }
        else if(station.type_prise == "T2-E/F" || station.type_prise == "EF, T2" || station.type_prise == "EF - T2") {
            imageview.setImageResource(R.drawable.t2_ef)
        }
        else if(station.type_prise == "T2") {
            imageview.setImageResource(R.drawable.t2)
        }
        else if(station.type_prise == "T2P") {
            imageview.setImageResource(R.drawable.t2p)
        }
        else {
            imageview.setImageResource(R.drawable.ef)
        }

        //Favorite
        val toggleFavorite = {
            val isFavorite = !station.favorite
            val body = FavoriteRequest(isFavorite)

            stationService.toggleFavorite(station.id_station, body).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Mise à jour du statut favori
                        station.favorite = isFavorite
                        // Mise à jour de l'interface utilisateur
                        val drawableRes = if (station.favorite) {
                            R.drawable.like
                        } else {
                            R.drawable.unlike
                        }
                        imageLike.setImageResource(drawableRes)

                        Toast.makeText(baseContext, "Favori mis à jour avec succès", Toast.LENGTH_SHORT).show()

                        // Retourner la station mise à jour à l'activité principale
                        intent.putExtra(UPDATED_STATION, station)
                        setResult(RESULT_OK, intent)
                        //finish()
                    } else {
                        Toast.makeText(baseContext, "Erreur lors de la mise à jour", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("Retrofit", "Failure: ${t.message}")
                    Toast.makeText(baseContext, "Erreur réseau : ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        btnAddFav.setOnClickListener {
            toggleFavorite()
        }

        imageLike.setOnClickListener {
            toggleFavorite()
        }

        backButton.setOnClickListener {
            finish()
        }

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