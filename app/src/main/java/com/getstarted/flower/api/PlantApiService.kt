package com.getstarted.flower.api

import com.getstarted.flower.data.Plant
import retrofit2.http.GET

interface PlantApiService {

    @GET("plants") // Endpoint for fetching plants
    suspend fun getPlants(): List<Plant> // Adjust return type according to your API response
}
