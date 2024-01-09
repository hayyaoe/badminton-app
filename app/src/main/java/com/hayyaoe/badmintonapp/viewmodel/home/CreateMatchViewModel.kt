package com.hayyaoe.badmintonapp.viewmodel.home

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.CreateGameResponse
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.GameData
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.model.Scores
import com.hayyaoe.badmintonapp.model.Set
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

sealed interface CreateMatchUiState{
    object Loading: CreateMatchUiState
    data class Success(val game: Game, val player: Player, val opponent: Player?): CreateMatchUiState
    object Error: CreateMatchUiState
}

class CreateMatchViewModel : ViewModel(){
    private val _setState = MutableStateFlow<List<Set>>(emptyList())
    val setState :StateFlow<List<Set>> = _setState.asStateFlow()
    var createMatchUiState: CreateMatchUiState by mutableStateOf(CreateMatchUiState.Loading)

    private lateinit var game:Game
    private lateinit var player: Player
    private var opponent: Player? = null

    init {
        loadData()
    }

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }

    fun loadData() {
        viewModelScope.launch {
            val gameData = BadmintonContainer().badmintonRepositories.create_game()
            game = Game(
                id = gameData.game_id,
                score_1 = gameData.score_1,
                score_2 = gameData.score_2,
                created_at = gameData.created_at,
                updated_at= gameData.updated_at,
                information = gameData.information,
                gamecode = gameData.gamecode,
                gamestatus = gameData.gamestatus
            )
            player = gameData.players?.get(0) ?: Player("",0,"user")

            createMatchUiState = CreateMatchUiState.Success(game, player, opponent)
        }

    }

    fun createSet(game_id : Int){
        viewModelScope.launch {
            val oldvalue = _setState.value
            val newset = BadmintonContainer().badmintonRepositories.create_set(game_id)
            _setState.value = oldvalue+ newset
        }
    }

    fun updateSet(set_id: Int, score_1: String, score_2: String, sets: List<Set>){

        if (score_2.isNotBlank() && score_1.isNotBlank()){
            viewModelScope.launch {
                val oldValue = _setState.value.toMutableList()
                val updatedValue = BadmintonContainer().badmintonRepositories.update_set(set_id,score_1.toInt(),score_2.toInt())
                Log.d("UPDATED VALUE", updatedValue.toString())
                for ((index, set) in oldValue.withIndex()){
                    if (set.id == set_id){
                        oldValue[index] = updatedValue
                    }
                }
                _setState.value = oldValue

                val scores = getScore(_setState.value)
                Log.d("SCORES AFTER CALCULATED", scores.toString())
                val gameData = BadmintonContainer().badmintonRepositories.update_game(game.gamecode,game.gamestatus,game.information,scores.score1,scores.score2)
                Log.d("UPDATED GAME DATA", gameData.toString())
                game = Game(
                    id = gameData.game_id,
                    score_1 = gameData.score_1,
                    score_2 = gameData.score_2,
                    created_at = gameData.created_at,
                    updated_at= gameData.updated_at,
                    information = gameData.information,
                    gamecode = gameData.gamecode,
                    gamestatus = gameData.gamestatus
                )
                if (gameData.players != null){
                    if (gameData.players.size > 1){
                        opponent = gameData.players[1]
                    }
                }


                createMatchUiState = CreateMatchUiState.Success(game, player, opponent)
            }
        }

    }

    fun getScore(sets: List<Set>): Scores {
        val scores = Scores()
        for (set in sets){
            if (set.player1_score>set.player2_score){
                scores.score1 +=1
            }else if (set.player1_score<set.player2_score){
                scores.score2 +=1
            }
        }
        return scores
    }

//    fun addScore(set1score1: String, set1score2: String, set2score1: String, set2score2: String, set3score1: String?,set3score2: String?){
//        Log.d("ADD SCORE TRIGGERED", "SUCCESS")
//        val currentData = _uiState.value
//        if (set1score1.isNotBlank() && set1score2.isNotBlank()){
//            if (set1score1.toInt() > set1score2.toInt()){
//                game.score_1 +=1
//                set1.player1_score = set1score1.toInt()
//                set1.player2_score = set1score2.toInt()
//            }else{
//                game.score_2 +=1
//                set1.player1_score = set1score1.toInt()
//                set1.player2_score = set1score2.toInt()
//            }
//        }
//
//        if (set2score1.isNotBlank() && set2score2.isNotBlank()){
//            if (set2score1.toInt() > set2score2.toInt()){
//                game.score_1 +=1
//                set2.player1_score = set2score1.toInt()
//                set2.player2_score = set2score2.toInt()
//            }else{
//                game.score_2 +=1
//                set2.player1_score = set2score1.toInt()
//                set2.player2_score = set2score2.toInt()
//            }
//        }
//
//        if (!set3score1.isNullOrBlank() && !set3score2.isNullOrBlank() && set3 != null) {
//            if (set3score1.toInt() > set3score2.toInt()){
//                game.score_1 +=1
//                set3!!.player1_score = set3score1.toInt()
//                set3!!.player2_score = set3score2.toInt()
//            }else{
//                game.score_2 +=1
//                set3!!.player1_score = set3score1.toInt()
//                set3!!.player2_score = set3score2.toInt()
//            }
//        }
//
//        val newUiState = CreateMatchUiState.Success(
//            game,
//            set1,
//            set2,
//            set3,
//            user1,
//            user2
//        )

//    }



}