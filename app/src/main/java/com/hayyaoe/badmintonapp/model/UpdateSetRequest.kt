package com.hayyaoe.badmintonapp.model

data class UpdateSetRequest(
    val id: Int,
    val player1_score: Int,
    val player2_score: Int
)