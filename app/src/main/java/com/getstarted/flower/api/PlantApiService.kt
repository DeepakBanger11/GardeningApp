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

    @GET("api/species-list?key=sk-Qvvy656e106d4eaf03310") // Endpoint for fetching plants
    suspend fun getPlantData(
        @Query("page") plant: Int,
        @Query("q") query: String
    ): PlantJsonResponse// Adjust return type according to your API response sk-0wTF656a0cbd1a9a63258

    @GET("species/details/{ID}?key=sk-Qvvy656e106d4eaf03310")
    suspend fun getPlantDetails(
        @Path("ID") id: Int
    ): SpeciesDetails

}

