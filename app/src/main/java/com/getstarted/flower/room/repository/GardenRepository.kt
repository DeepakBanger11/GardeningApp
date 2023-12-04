package com.getstarted.flower.room.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import com.getstarted.flower.api.PlantApiService
import com.getstarted.flower.api.model.Data

import com.getstarted.flower.api.model.PlantJsonResponse
import com.getstarted.flower.api.model.detail.SpeciesDetails
import com.getstarted.flower.api.model.paging.PlantPagingSource
import com.getstarted.flower.data.Plant
import com.getstarted.flower.data.PlantDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject

@ViewModelScoped
open //Scope annotation for bindings that should exist for the life of a a single ViewModel.
class GardenRepository @Inject constructor(
    private val plantDao: PlantDao,
    private val plantApiService: PlantApiService
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

    suspend fun getPlantData(page:Int,query: String): PlantJsonResponse {
        return plantApiService.getPlantData(page,query)
    }
    suspend fun getPlantDetails(id:Int): SpeciesDetails {
        return plantApiService.getPlantDetails(id)
    }
    fun getPlantsPaging(query: String): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { PlantPagingSource(plantApiService,query) }
        ).flow
    }
}
