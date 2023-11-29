package com.getstarted.flower.api

data class JsonResults(val data: List<Plant>)

data class Plant(
    val id: Int,
    val common_name: String,
    val scientific_name: List<String>,
    val other_name: List<String>,
    val cycle: String,
    val watering: String,
    val sunlight: List<String>,
    val default_image: PlantImage
)

data class PlantImage(
    val license: Int,
    val license_name: String,
    val license_url: String,
    val original_url: String
)