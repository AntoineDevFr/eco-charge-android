package com.example.eco_charge_android

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STATIONS = "stations"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInfo : Fragment() {
    private lateinit var stations: ArrayList<Station>

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
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)
        val txvDataApp = rootView.findViewById<TextView>(R.id.r_data_app)
        val dataApp = "Lien des données : https://data.opendatasoft.com/explore/dataset/bornes-irve%40reseaux-energies-rte/table/?disjunctive.region&disjunctive.departement&sort=n_amenageur&location=6,47.1449,-0.38452&basemap=jawg.streets\n\n" +
                "Application crée dans un but éducatif par Antoine Banchet et Elliot Galaor\n\n" +
                "Github App : https://github.com/AntoineDevFr/eco-charge-android\n" +
                "Github Serveur : https://github.com/AntoineDevFr/eco-charge-web"
        txvDataApp.text = dataApp
        txvDataApp.movementMethod = LinkMovementMethod.getInstance()
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(stations: ArrayList<Station>) =
            FragmentInfo().apply {
                arguments = Bundle().apply {
                    putSerializable(STATIONS, stations)
                }
            }
    }
}