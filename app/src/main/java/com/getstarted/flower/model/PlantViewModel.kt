package com.getstarted.flower.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.getstarted.flower.data.Plant
import com.getstarted.flower.room.repository.GardenRepository
import kotlinx.coroutines.launch

class PlantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GardenRepository = GardenRepository(application)

    val plantList: LiveData<List<Plant>> = repository.getAllPlants()

    init {
        viewModelScope.launch {
            repository.populateDatabaseFromJson()
        }
    }
}