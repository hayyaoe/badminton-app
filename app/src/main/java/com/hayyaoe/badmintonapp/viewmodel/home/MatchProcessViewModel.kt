package com.hayyaoe.badmintonapp.viewmodel.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.GameData
import com.hayyaoe.badmintonapp.model.GetGameData
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.ListScreen
import kotlinx.coroutines.launch

sealed interface MatchProcessUiState{
    data class Success (val userData: Player, val game: GetGameData): MatchProcessUiState
    object Loading : MatchProcessUiState
    object Error : MatchProcessUiState
}




class MatchProcessViewModel : ViewModel(){
    var matchProcessUiState : MatchProcessUiState by  mutableStateOf(MatchProcessUiState.Loading)

    lateinit var player: Player
    lateinit var games: GetGameData
    init{
       loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            val gameDatas = BadmintonContainer().badmintonRepositories.get_game_datas(BadmintonContainer.GAMECODE)
            player = gameDatas.players?.get(0) ?: Player("",0,"opponent")
            games = gameDatas
            matchProcessUiState = MatchProcessUiState.Success(player, games)
        }

    }

    fun ConfirmGame (navController: NavController){
        viewModelScope.launch {
            val game = BadmintonContainer().badmintonRepositories.get_game_datas(BadmintonContainer.GAMECODE)
            val gameResponse = BadmintonContainer().badmintonRepositories.update_game(game.gamecode, gamestatus = 2,game.information,game.score_1,game.score_2)
            Log.d("CHECK CONFIRM GAME", gameResponse.toString())

            if (gameResponse.gamestatus == 2){
                navController.navigate(ListScreen.CommentView.name){
                    popUpTo(ListScreen.CreateMatchView.name)
                }
            }


        }
    }

    fun isGameConfirmed (): Boolean{
        var confirm = false
        viewModelScope.launch {
            val gameData = BadmintonContainer().badmintonRepositories.get_game_datas(BadmintonContainer.GAMECODE)
            Log.d("IS GAME DATA SENT", gameData.toString())
            if (gameData.gamestatus == 1){
                player = gameData.players?.get(0) ?: Player("",0,"opponent")
                games = gameData
                matchProcessUiState = MatchProcessUiState.Success( player, games)
            }
        }

        if (games.gamestatus == 1){
            confirm = true
        }
        return confirm

    }

    fun isWon(score1: Int, score2: Int):Boolean{
        return score1> score2
    }
}