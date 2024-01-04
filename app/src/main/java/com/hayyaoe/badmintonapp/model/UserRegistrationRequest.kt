package com.hayyaoe.badmintonapp.model

data class UserRegistrationRequest(
    val username: String,
    val email: String,
    val password: String,
    val rank: Int,
    val contacts: String,
    val phone_number: String
)
