package com.getstarted.flower.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getstarted.flower.R
import com.getstarted.flower.adapter.PlantListAdapter
import com.getstarted.flower.data.PlantJson
import com.getstarted.flower.model.PlantViewModel
import com.getstarted.flower.utils.Constants.DESCRIPTION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPlantsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_plants, container, false)
        val plantViewModel: PlantViewModel by viewModels()

        val recyclerView: RecyclerView = view.findViewById(R.id.plantRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        plantViewModel.getPlantData()
        plantViewModel.plantJsonResponse.observe(viewLifecycleOwner) { plantJsonResponse ->
            val plantList = plantJsonResponse.data.map { data ->
                PlantJson(
                    plantId =data.common_name.toString(),
                    name = data.common_name.toString(),
                    wateringInterval = data.watering.toString(),
                    description = DESCRIPTION,
                    growZoneNumber = data.cycle.toString(),
                    imageUrl = data.default_image?.original_url ?: "no image"
                )
            }
            recyclerView.adapter = PlantListAdapter(requireContext(), plantList)
        }
        return view

    }

}