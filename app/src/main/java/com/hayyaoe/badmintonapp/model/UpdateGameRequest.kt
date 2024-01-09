package com.hayyaoe.badmintonapp.model

data class UpdateGameRequest(
    val gamecode: String,
    val gamestatus: Int,
    val information: String,
    val score_1: Int,
    val score_2: Int
)