package com.hayyaoe.badmintonapp.model

data class UpdateSpartnerResponse(
    val created_at: String,
    val id: Int,
    val updated_at: String,
    val user1: Int,
    val user1status: Int,
    val user2: Int,
    val user2status: Int
)