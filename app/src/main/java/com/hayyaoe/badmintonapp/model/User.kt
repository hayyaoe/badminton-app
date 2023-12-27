package com.hayyaoe.badmintonapp.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val contacts: String = "",
    val created_at: String = "",
    val email: String = "",
    val id: Int = 0,
    val image_path: String = "",
    val phone_number: String = "",
    val rank: Int= 0,
    val updated_at: String = "",
    val username: String = "",
    val password: String = ""
)