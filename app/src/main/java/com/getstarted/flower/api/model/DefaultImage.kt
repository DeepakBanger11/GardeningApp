package com.getstarted.flower.api.model

import com.google.gson.annotations.SerializedName

data class DefaultImage(
    @SerializedName("license")
    val license: Int,
    @SerializedName("license_name")
    val license_name: String,
    @SerializedName("license_url")
    val license_url: String,
    @SerializedName("medium_url")
    val medium_url: String,
    @SerializedName("original_url")
    val original_url: String,
    @SerializedName("regular_url")
    val regular_url: String,
    @SerializedName("small_url")
    val small_url: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)