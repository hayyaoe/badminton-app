package com.hayyaoe.badmintonapp.model

data class OtherUserData(
    val `data`: List<OtherUser>
)

data class OtherUser(
    val contacts: String,
    val id: Int,
    val location_id: Int,
    val phone_number: String,
    val profile_path: String?,
    val rank: Int,
    val username: String
)