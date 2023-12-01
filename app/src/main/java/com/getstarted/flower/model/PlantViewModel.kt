package com.getstarted.flower.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.getstarted.flower.api.model.Data
import com.getstarted.flower.api.model.PlantJsonResponse
import com.getstarted.flower.api.model.detail.SpeciesDetails
import com.getstarted.flower.data.Plant
import com.getstarted.flower.data.PlantJson
import com.getstarted.flower.room.repository.GardenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val repository: GardenRepository,
) : ViewModel() {


    val getAllPlant: LiveData<List<Plant>> = repository.getAllPlant

    private val _plantJsonResponse = MutableLiveData<PlantJsonResponse>()
    val plantJsonResponse: LiveData<PlantJsonResponse> = _plantJsonResponse
    private val _speciesDetailsResponse = MutableLiveData<SpeciesDetails>()
    val speciesDetailsResponse: LiveData<SpeciesDetails> = _speciesDetailsResponse

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

    fun getPlantData(page:Int) {
        try {
            viewModelScope.launch {
                val response = apiRequestWithRetry { repository.getPlantData(page) }
                _plantJsonResponse.postValue(response)
            }
        } catch (e:IOException){

        }
    }

    suspend fun <T> apiRequestWithRetry(
        retryCount: Int = 3,
        initialDelayMillis: Long = 1000L,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelayMillis
        repeat(retryCount) { attempt ->
            try {
                return block()
            } catch (e: HttpException) {
                if (e.code() == 429) {
                    // Exponential backoff: Increase delay exponentially between retries
                    delay(currentDelay)
                    currentDelay *= 2
                } else {
                    throw e
                }
            }
        }
        throw IOException("Failed after $retryCount attempts")
    }
    fun getPlantsPaging(): Flow<PagingData<Data>> {
        return repository.getPlantsPaging().cachedIn(viewModelScope)
    }

     fun getPlantDetails(id:String) {
        try {
            viewModelScope.launch {
                val response = apiRequestWithRetry { repository.getPlantDetails(id) }
                _speciesDetailsResponse.postValue(response)
            }
        } catch (e:IOException){
        }
    }
}