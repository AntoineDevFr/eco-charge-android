package com.example.eco_charge_android

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stations = it.getSerializable(STATIONS) as ArrayList<Station>

            stations.sortByDescending { it.favorite }
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedStation = result.data?.getSerializableExtra(UPDATED_STATION) as Station
            // Mise à jour de la station dans la liste
            updateStationInList(updatedStation)
        }
    }

    private fun updateStationInList(updatedStation: Station) {
        val index = stations.indexOfFirst { it.id_station == updatedStation.id_station }

        if (index != -1) {
            stations[index] = updatedStation

            stations.sortByDescending { it.favorite }

            (activity as? MainActivity)?.updateStationShelf(stations)

            stationAdapter.notifyDataSetChanged()

        }
    }


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

    private fun filterStations(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            stations
        } else {
            stations.filter { station ->
                station.n_station.contains(query, ignoreCase = true)
            }
        }

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