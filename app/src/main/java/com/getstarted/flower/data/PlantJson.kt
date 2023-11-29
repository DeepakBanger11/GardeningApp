package com.getstarted.flower.data

data class PlantJson(
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: String,
    val wateringInterval: String,
    val imageUrl: String = ""
)
