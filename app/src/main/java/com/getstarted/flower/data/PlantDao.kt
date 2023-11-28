package com.getstarted.flower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
// data access object
interface PlantDao {
    @Query("SELECT * FROM garden_table ORDER BY id ASC")
    fun getAllPlants(): LiveData<List<Plant>>

    //Flow:-An asynchronous data stream that sequentially emits values and completes normally or with an exception
    @Query("SELECT * FROM garden_table WHERE id =:taskId")
    fun getSelectedPlant(taskId: Int):  LiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlant(plant: Plant)


    @Delete
    suspend fun deletePlant(plant: Plant)


}


