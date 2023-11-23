package com.getstarted.flower.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getstarted.flower.model.PlantData

class PlantViewModel : ViewModel() {
    val plants = MutableLiveData<List<PlantData>>()
    val plantLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchPlants()
    }

    private fun fetchPlants() {
        val mockData:List<PlantData> = listOf(
            PlantData("Rose"),
            PlantData("Lilly"),
            PlantData("Rosemary"),
            PlantData("Lotus"),
            PlantData("Sunflower"),
            PlantData("Cactus"),
            PlantData("Mango"),
            PlantData("Apple"),
            PlantData("Olive")
        )
        plantLoadError.value = false
        loading.value = false
        plants.value = mockData
    }
}