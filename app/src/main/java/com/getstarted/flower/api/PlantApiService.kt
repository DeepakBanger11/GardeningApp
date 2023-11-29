package com.getstarted.flower.api

import com.getstarted.flower.data.Plant
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PlantApiService {

    @GET("api/species-list?key=sk-ERee65676e56713b13195") // Endpoint for fetching plants
     fun getPlantData():Call<JsonResults> // Adjust return type according to your API response
}
