package com.getstarted.flower.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.getstarted.flower.data.Plant
import com.getstarted.flower.data.PlantDao

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class GardenDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}

