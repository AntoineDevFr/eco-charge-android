package com.example.eco_charge_android.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eco_charge_android.R
import com.example.eco_charge_android.adapter.StationAdapter
import com.example.eco_charge_android.model.Station

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STATIONS = "stations"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentListe.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentListe : Fragment() {
    private lateinit var stations: ArrayList<Station>
    private lateinit var stationAdapter: StationAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    /**
     * Initialisation du fragment. Trie la liste des stations en fonction de leur statut favori.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stations = it.getSerializable(STATIONS) as ArrayList<Station>
            // Tri des stations pour afficher les favoris en premier
            stations.sortByDescending { it.favorite }
        }
    }

    /**
     * Gestion des résultats de l'activité DetailsStationActivity lancée depuis ce fragment, permettant la mise à jour des stations favorites.
     */
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedStation = result.data?.getSerializableExtra(UPDATED_STATION) as Station
            // Mise à jour de la station dans la liste
            updateStationInList(updatedStation)
        }
    }

    /**
     * Met à jour une station existante dans la liste et réorganise les données dans l'adapter.
     */
    private fun updateStationInList(updatedStation: Station) {
        val index = stations.indexOfFirst { it.id_station == updatedStation.id_station }

        if (index != -1) {
            stations[index] = updatedStation
            // Trie les stations pour afficher les favoris en premier
            stations.sortByDescending { it.favorite }
            // Met à jour l'affichage global dans l'activité principale (stationShelf)
            (activity as? MainActivity)?.updateStationShelf(stations)
            // Notifie l'adaptateur des changements
            stationAdapter.notifyDataSetChanged()

        }
    }

    /**
     * Crée et retourne la vue associée au fragment.
     * Initialise le RecyclerView et le SearchView.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_liste, container, false)

        recyclerView = rootView.findViewById(R.id.f_list_rcv_station)
        stationAdapter = StationAdapter(stations) { station ->
            val intent = Intent(context, DetailsStationActivity::class.java).apply {
                putExtra("DATA_KEY", station)
            }
            startForResult.launch(intent)
        }

        recyclerView.adapter = stationAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        // Initialisation du SearchView pour la recherche des stations
        searchView = rootView.findViewById(R.id.search_view)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Ignorer la soumission du texte
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filtrer les résultats
                filterStations(newText)
                return true
            }
        })

        return rootView
    }

    /**
     * Filtre la liste des stations en fonction de la requête saisie par l'utilisateur.
     */
    private fun filterStations(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            stations
        } else {
            stations.filter { station ->
                station.n_station.contains(query, ignoreCase = true)
            }
        }
        // Met à jour l'adaptateur avec la liste filtrée
        stationAdapter.updateList(ArrayList(filteredList))
    }

    companion object {
        @JvmStatic
        fun newInstance(stations: ArrayList<Station>) =
            FragmentListe().apply {
                arguments = Bundle().apply {
                    putSerializable(STATIONS, stations)
                }
            }
    }
}