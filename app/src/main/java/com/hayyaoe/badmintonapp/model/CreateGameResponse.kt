package com.hayyaoe.badmintonapp.model

data class CreateGameResponse(
    val game_id: Int,
    val gamecode: String,
    val information: String,
    val players: List<Player>,
    val score_1: Int,
    val score_2: Int
)