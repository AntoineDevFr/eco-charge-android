package com.example.eco_charge_android.ui

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.eco_charge_android.R
import com.example.eco_charge_android.model.Station

// Constante utilisée pour passer des paramètres à l'instance du fragment
private const val STATIONS = "stations"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInfo : Fragment() {
    private lateinit var stations: ArrayList<Station>
    private lateinit var opendataImage: ImageView
    private lateinit var minesImage: ImageView

    /**
     * Initialisation du fragment. Récupère la liste des stations.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stations = it.getSerializable(STATIONS) as ArrayList<Station>
        }
    }

    /**
     * Crée et retourne la vue associée au fragment.
     * Initialise les composants visuels (TextView et ImageView) et configure leur contenu.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)

        val txvDataApp = rootView.findViewById<TextView>(R.id.r_data_app)
        txvDataApp.movementMethod = LinkMovementMethod.getInstance()

        opendataImage = rootView.findViewById(R.id.opendata_View)
        opendataImage.setImageResource(R.drawable.opendata)

        minesImage = rootView.findViewById(R.id.mines_view)
        minesImage.setImageResource(R.drawable.mines)
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