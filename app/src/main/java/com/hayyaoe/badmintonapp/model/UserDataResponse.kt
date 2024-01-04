package com.hayyaoe.badmintonapp.model

import kotlinx.serialization.Serializable


@Serializable
data class UserDataResponse(
    val username: String,
    val email: String,
    val rank: Int,
    val contacts: String?,
    val phone_number: String?, // Assuming it can be represented as a String
    val updated_at: String,
    val created_at: String,
    val id: Int
)

// Assuming the token is a String
data class TokenResponse(
    val token: String
)
