package com.example.eco_charge_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STATIONS = "stations"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentMap.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentMap : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_liste, container, false)

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentMap.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Station>) =
            FragmentMap().apply {
                arguments = Bundle().apply {
                    putSerializable(STATIONS, stations)
                }
            }
    }
}