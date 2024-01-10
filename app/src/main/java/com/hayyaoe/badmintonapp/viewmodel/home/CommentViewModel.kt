package com.hayyaoe.badmintonapp.viewmodel.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.model.CreateGameResponse
import com.hayyaoe.badmintonapp.model.GetGameData
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch

sealed interface CommentUiState{
    object Loading : CommentUiState
    data class Success(val opponent: Player, val game: GetGameData): CommentUiState
    object Error : CommentUiState

}
class CommentViewModel : ViewModel(){

    var commentUiState: CommentUiState by mutableStateOf(CommentUiState.Loading)
    lateinit var opponent: Player
    lateinit var game: GetGameData
        init{
            loadData()
        }

        fun loadData(){
            viewModelScope.launch {
                val gameDatas = BadmintonContainer().badmintonRepositories.get_game_datas(BadmintonContainer.GAMECODE)
                val user = BadmintonContainer().badmintonRepositories.get_user()
               val players = gameDatas.players
                for (player in players){
                    if (player.user_id!= user.id){
                        opponent = player
                        break
                    }
                }
                 gameDatas.players?.get(0) ?: Player("",0,"opponent")

                game = gameDatas
                commentUiState = CommentUiState.Success(opponent, game)
            }

        }

    fun postComment(userId: Int, gameID: Int, comment: String, navController: NavController){
        viewModelScope.launch {
            val response = BadmintonContainer().badmintonRepositories.create_review(user_id = userId, game_id = gameID, review = comment)
            Log.d("POST REVIEW", response.message)
            if (response.message == ("Review Created")){
                navController.navigate("home"){
                    popUpTo("home"){
                        inclusive = true
                    }
                }
            }
        }
    }

}