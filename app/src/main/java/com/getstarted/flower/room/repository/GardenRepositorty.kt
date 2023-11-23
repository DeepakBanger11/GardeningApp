package com.getstarted.flower.room.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.getstarted.flower.data.Plant
import com.getstarted.flower.room.GardenDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GardenRepository(private val context: Context) {

    suspend fun populateDatabaseFromJson() {
        val plantListJson = context.assets.open("plants.json").bufferedReader().use { it.readText() }
        val plantList: List<Plant> = Gson().fromJson(plantListJson, object : TypeToken<List<Plant>>() {}.type)
        val plantEntityList: List<Plant> = plantList.map { plant ->
            Plant(
                plant.plantId,
                plant.name,
                plant.description,
                plant.growZoneNumber,
                plant.wateringInterval,
                plant.imageUrl
            )
        }
        val database = GardenDatabase.getDatabase(context)
        database.plantDao().insertPlants(plantEntityList)
    }

    fun getAllPlants(): LiveData<List<Plant>> {
        val database = GardenDatabase.getDatabase(context)
        return database.plantDao().getAllPlants()
    }
}