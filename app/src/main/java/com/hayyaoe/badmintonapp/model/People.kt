package com.hayyaoe.badmintonapp.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("name") val name: String,
    @SerializedName("profilePicture") val profilePicture: String,
    @SerializedName("location") val location: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("instagram") val instagram: String,
)