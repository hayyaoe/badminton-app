package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                val gameDatas = BadmintonContainer().badmintonRepositories.get_game_datas(
                    BadmintonContainer.GAMECODE)
                opponent = gameDatas.players?.get(0) ?: Player("",0,"opponent")
                game = gameDatas

                commentUiState = CommentUiState.Success(opponent, game)
            }

        }

}