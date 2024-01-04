package com.hayyaoe.badmintonapp.model

import kotlinx.serialization.Serializable

data class User(
    val contacts: String = "",
    val email: String = "",
    val image_path: String = "",
    val phone_number: String = "",
    val rank: Int= 0,
    val username: String = "",
    val password: String = ""
)