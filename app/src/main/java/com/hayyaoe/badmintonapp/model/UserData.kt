package com.hayyaoe.badmintonapp.model

import com.google.gson.annotations.SerializedName

data class UserData(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val image_path: String? = null,
    val email_verified_at: String? = null,
    val contacts: String? = null,
    val phone_number: String?= null,
    val rank: Int = 0,
    val location_id: Int? = null,
    val created_at: String = "",
    val updated_at: String = ""
)