package com.getstarted.flower.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.LocalContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getstarted.flower.R
import com.getstarted.flower.adapter.PlantListAdapter
import com.getstarted.flower.data.Plant
import com.google.gson.Gson
import java.io.IOException


class MyPlantsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_plants, container, false)

        // Sample data from JSON (replace with your actual data)

        val plantList = loadPlantsFromJson(requireContext())
        val recyclerView: RecyclerView = view.findViewById(R.id.plantRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = PlantListAdapter(requireContext(),plantList)

        return view

    }
    private fun loadPlantsFromJson(context: Context): List<Plant> {
        val jsonFileName = "plants.json"
        val jsonString: String
        try {
            val inputStream = context.assets.open(jsonFileName)
            jsonString = inputStream.bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        return Gson().fromJson(jsonString, Array<Plant>::class.java).toList()
    }
}