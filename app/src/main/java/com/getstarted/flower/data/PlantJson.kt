package com.getstarted.flower.data

import androidx.room.PrimaryKey

data class PlantJson(
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int,
    val imageUrl: String = ""
)
