package com.example.eco_charge_android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stations = it.getSerializable(STATIONS) as ArrayList<Station>
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
            startActivity(intent)
        }

        recyclerView.adapter = stationAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        return rootView
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