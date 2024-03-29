package com.hayyaoe.badmintonapp.model

data class UpdateUser(
    val contacts: String,
    val email: String,
    val location_id: Int,
    var image_path: String?,
    val password: String?,
    val phone_number: String,
    val rank: Int,
    val username: String
)