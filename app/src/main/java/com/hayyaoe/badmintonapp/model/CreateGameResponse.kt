package com.hayyaoe.badmintonapp.model

data class CreateGameResponse(
    val game_id: Int,
    val gamecode: String,
    val gamestatus: Int,
    val information: String,
    val created_at: String,
    val updated_at: String,
    val players: List<Player>?,
    val score_1: Int,
    val score_2: Int
)