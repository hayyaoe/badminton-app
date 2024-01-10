package com.hayyaoe.badmintonapp.model

data class Spartner(
    val contacts: String,
    val created_at: String,
    val email: String,
    val email_verified_at: String?,
    val id: Int,
    val image_path: String?,
    val location_id: String?,
    val phone_number: String,
    val rank: Int,
    val updated_at: String,
    val username: String
)