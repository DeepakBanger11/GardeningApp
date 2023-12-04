package com.getstarted.flower.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getstarted.flower.R
import com.getstarted.flower.adapter.PlantListAdapter
import com.getstarted.flower.data.PlantJson
import com.getstarted.flower.model.PlantViewModel
import com.getstarted.flower.utils.Constants.DESCRIPTION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPlantsFragment : Fragment() {
    private val plantViewModel: PlantViewModel by viewModels()
    private lateinit var plantListAdapter: PlantListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_plants, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.plantRecyclerView)
        val searchView: SearchView = view.findViewById(R.id.searchView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        plantListAdapter = PlantListAdapter()
        recyclerView.adapter = plantListAdapter // Set the adapter to RecyclerView

        lifecycleScope.launch {
            plantViewModel.getPlantsPaging().collectLatest { pagingData ->
                plantListAdapter.submitData(pagingData)
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Update LiveData with the new text
                plantViewModel.updateSearchQuery(newText.orEmpty()) // Use orEmpty() to handle null case
                return true
            }
        })

        plantViewModel.searchQuery.observe(viewLifecycleOwner) { query ->
            lifecycleScope.launch {
                plantViewModel.getPlantsPaging().collectLatest { pagingData ->
                    plantListAdapter.submitData(pagingData)
                }
            }
        }
    }
}

