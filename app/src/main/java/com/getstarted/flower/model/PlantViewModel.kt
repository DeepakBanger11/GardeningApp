package com.getstarted.flower.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getstarted.flower.api.model.PlantJsonResponse
import com.getstarted.flower.data.Plant
import com.getstarted.flower.room.repository.GardenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val repository: GardenRepository,
) : ViewModel() {


    val getAllPlant: LiveData<List<Plant>> = repository.getAllPlant

    private val _plantJsonResponse = MutableLiveData<PlantJsonResponse>()
    val plantJsonResponse: LiveData<PlantJsonResponse> = _plantJsonResponse

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

    fun getPlantData() {
        viewModelScope.launch {
            val response = repository.getPlantData()
            _plantJsonResponse.postValue(response)
        }
    }
//    fun getPlantsPaging(){
//        repository.getPlantsPaging()
//    }
}