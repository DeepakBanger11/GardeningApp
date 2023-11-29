package com.getstarted.flower.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getstarted.flower.R
import com.getstarted.flower.adapter.PlantListAdapter
import com.getstarted.flower.api.JsonResults
import com.getstarted.flower.api.Plant
import com.getstarted.flower.api.PlantApiService
import com.getstarted.flower.data.PlantJson
import com.getstarted.flower.utils.Constants.DESCRIPTION
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MyPlantsFragment : Fragment() {


    private lateinit var plantListAdapter: PlantListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_plants, container, false)

        // Sample data from JSON (replace with your actual data)
        val plantList = loadPlantsFromJson(requireContext())
        val recyclerView: RecyclerView = view.findViewById(R.id.plantRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
       // recyclerView.adapter = PlantListAdapter(requireContext(), plantList)
        fetchDataFromAPI(recyclerView)
        return view

    }

    private fun loadPlantsFromJson(context: Context): List<PlantJson> {
        val jsonFileName = "plants.json"
        val jsonString: String
        try {
            val inputStream = context.assets.open(jsonFileName)
            jsonString = inputStream.bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
        return Gson().fromJson(jsonString, Array<PlantJson>::class.java).toList()
    }

    private fun fetchDataFromAPI(recyclerView:RecyclerView) {
        var plantJson:PlantJson
        // Retrofit instance for API calls
        val retrofit = Retrofit.Builder()
            .baseUrl("https://perenual.com/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the API interface
        val plantAPI = retrofit.create(PlantApiService::class.java)

        // Make the API call asynchronously
        val call = plantAPI.getPlantData()

        call.enqueue(object : Callback<JsonResults> {
            override fun onResponse(call: Call<JsonResults>, response: Response<JsonResults>) {
                if (response.isSuccessful) {
                    val plantResponse = response.body()
                    plantResponse?.let {
                        val plantList: List<Plant> = it.data
                        // Process the plant data as needed
                        val plantJsonList = plantList.map { plant ->
                            PlantJson(
                                plantId = plant.scientific_name.joinToString(","),
                                name = plant.common_name,
                                description = DESCRIPTION,
                                growZoneNumber = plant.cycle,
                                wateringInterval = plant.watering,
                                imageUrl = plant.default_image?.original_url ?: "Default URL if null"

                            )
                        }
                        Log.e("api", "successful response: ${plantList}")
                        recyclerView.adapter = PlantListAdapter(requireContext(), plantJsonList)
                        // Convert Plant data to PlantJson (if needed) and update RecyclerView

                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("API_CALL", "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<JsonResults>, t: Throwable) {
                // Handle failure/error in making the request
                Log.e("API_CALL", "Failed to execute request: ${t.message}")
            }
        })
    }

}