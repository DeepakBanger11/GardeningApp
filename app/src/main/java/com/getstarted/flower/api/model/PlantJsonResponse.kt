package com.getstarted.flower.api.model

import com.google.gson.annotations.SerializedName

data class PlantJsonResponse(
    @SerializedName("current_page")
    val current_page: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("from")
    val from: Int,
    @SerializedName("last_page")
    val last_page: Int,
    @SerializedName("per_page")
    val per_page: Int,
    @SerializedName("to")
    val to: Int,
    @SerializedName("total")
    val total: Int
)