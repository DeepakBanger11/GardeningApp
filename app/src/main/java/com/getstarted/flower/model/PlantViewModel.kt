package com.getstarted.flower.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getstarted.flower.data.Plant
import com.getstarted.flower.room.repository.GardenRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val repository: GardenRepository,
) : ViewModel() {
    val getAllPlant: LiveData<List<Plant>> = repository.getAllPlant
    fun addPlant(plant: Plant) {
        viewModelScope.launch {
            repository.addPlant(plant)
        }
    }

    fun getSelectedPlant(taskId: Int): LiveData<List<Plant>> {
        return repository.getSelectedPlant(taskId)
    }

    fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            repository.deletePlant(plant)
        }
    }
}