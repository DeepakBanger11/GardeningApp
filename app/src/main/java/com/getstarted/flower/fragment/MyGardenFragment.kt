package com.getstarted.flower.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getstarted.flower.R
import com.getstarted.flower.adapter.GardenListAdapter
import com.getstarted.flower.adapter.PlantListAdapter
import com.getstarted.flower.data.Plant
import com.getstarted.flower.model.PlantViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyGardenFragment() : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val plantViewModel: PlantViewModel by viewModels()
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_garden, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.gardenRecyclerView)
        val deleteFabButton:FloatingActionButton = view.findViewById(R.id.deleteFabButton)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val gardenListAdapter = GardenListAdapter(requireContext(), emptyList())
        recyclerView.adapter = gardenListAdapter
        plantViewModel.getAllPlant.observe(viewLifecycleOwner, Observer<List<Plant>> { plantList ->
            gardenListAdapter.updateList(plantList)
        })
        return view
    }
}