package com.getstarted.flower.room.repository

import androidx.lifecycle.LiveData

import com.getstarted.flower.api.PlantApiService

import com.getstarted.flower.api.model.PlantJsonResponse
import com.getstarted.flower.data.Plant
import com.getstarted.flower.data.PlantDao
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
open //Scope annotation for bindings that should exist for the life of a a single ViewModel.
class GardenRepository @Inject constructor(
    private val plantDao: PlantDao,
    private val plantApiService: PlantApiService,
) {
    val getAllPlant: LiveData<List<Plant>> = plantDao.getAllPlants()
    fun getSelectedPlant(taskId: Int): LiveData<List<Plant>> {
        return plantDao.getSelectedPlant(taskId = taskId)
    }

    suspend fun addPlant(plant: Plant) {
        plantDao.addPlant(plant)
    }

    suspend fun deletePlant(plant: Plant) {
        plantDao.deletePlant(plant = plant)
    }

    suspend fun getPlantData(): PlantJsonResponse {
        return plantApiService.getPlantData()
    }

//    fun getPlantsPaging(): Flow<PagingData<PlantJson>> {
//        return Pager(
//            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
//            pagingSourceFactory = { PlantPagingSource(plantApiService) }
//        ).flow
//    }
}
