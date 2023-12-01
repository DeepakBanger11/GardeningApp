package com.getstarted.flower.api

import com.getstarted.flower.api.model.PlantJsonResponse
import com.getstarted.flower.api.model.detail.SpeciesDetails
import com.getstarted.flower.data.Plant
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantApiService {

    @GET("api/species-list?key=sk-0wTF656a0cbd1a9a63258") // Endpoint for fetching plants
    suspend fun getPlantData(
        @Query("page") plant: Int,
    ): PlantJsonResponse// Adjust return type according to your API response

    @GET("species/details/{ID}?key=sk-0wTF656a0cbd1a9a63258")
    suspend fun getPlantDetails(
        @Path("ID") id: String,
    ): SpeciesDetails
}

