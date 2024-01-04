package com.hayyaoe.badmintonapp.model

import com.google.gson.annotations.SerializedName

data class UserData(
    val id: Int,
    val username: String,
    val email: String,
    val image_path: String?,
    val email_verified_at: String?,
    val contacts: String?,
    val phone_number: String?,
    val rank: Int,
    val location_id: Int?,
    val created_at: String,
    val updated_at: String
)