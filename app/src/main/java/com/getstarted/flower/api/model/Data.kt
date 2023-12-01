package com.getstarted.flower.api.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("common_name")
    val common_name: String,
    @SerializedName("cycle")
    val cycle: String,
    @SerializedName("default_image")
    val default_image: DefaultImage,
    @SerializedName("id")
    val id: Int,
    @SerializedName("other_name")
    val other_name: List<String>,
    @SerializedName("scientific_name")
    val scientific_name: List<String>,
    @SerializedName("sunlight")
    val sunlight: List<String>,
    @SerializedName("watering")
    val watering: String
)