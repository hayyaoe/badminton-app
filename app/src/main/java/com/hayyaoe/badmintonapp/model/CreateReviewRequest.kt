package com.hayyaoe.badmintonapp.model

data class CreateReviewRequest(
    val game_id: Int,
    val review: String,
    val user_id: Int
)