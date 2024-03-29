package com.hayyaoe.badmintonapp.model

data class CreateGameResult(
    val game_id: Int,
    val gamecode: String,
    val information: String,
    val score_1: Int,
    val score_2: Int,
    val set1_id: Int,
    val set2_id: Int
)


data class GameData(
    val game: Game,
    val user1: UserData,
    val user2: UserData?,
    val set1: Set,
    val set2: Set,
    val set3: Set?
)

data class Game(
    val id: Int,
    var score_1: Int,
    var score_2: Int,
    val created_at: String,
    val updated_at: String,
    val information: String,
    val gamecode: String,
    val gamestatus: Int,
)

data class Set(
    var player1_score: Int,
    var player2_score: Int,
    val created_at: String,
    val updated_at: String,
    val id: Int,
    val game_id: Int
)

data class GetSets(
    val game_id: Int
)

data class Sets(
    val data: List<Set>
)

data class UserGames(
    val data: List<UserGame>
)

data class History(
    val userGames : List<Data>
)

data class Data(
    val id: Int,
    val created_at: String,
    val updated_at: String,
    val user_id: Int,
    val game_id: Int,
    val game: Game,
    val user: UserData
)

data class UserGame(
    val id: Int,
    val created_at: String,
    val updated_at: String,
    val user_id: Int,
    val game_id: Int,
)


data class Games(
    val data :List<Game>,
    val data2 :List<UserData>,
    val userGames: List<UserGame>
)