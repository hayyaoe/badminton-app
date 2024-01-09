package com.hayyaoe.badmintonapp.model

data class GetGameData(
    val created_at: String,
    val game_id: Int,
    val gamecode: String,
    val gamestatus: Int,
    val information: String,
    val players: List<PlayerX>,
    val score_1: Int,
    val score_2: Int,
    val sets: List<Set>,
    val updated_at: String
)