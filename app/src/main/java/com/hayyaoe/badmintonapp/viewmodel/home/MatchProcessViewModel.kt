package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch

sealed interface MatchProcessUiState{
    data class Success (val userData: Player): MatchProcessUiState
    object Loading : MatchProcessUiState
    object Error : MatchProcessUiState
}




class MatchProcessViewModel : ViewModel(){
    var matchProcessUiState : MatchProcessUiState by  mutableStateOf(MatchProcessUiState.Loading)

    lateinit var player: Player

    init{
       loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            val gameDatas = BadmintonContainer().badmintonRepositories.get_game_datas(BadmintonContainer.GAMECODE)
            player = gameDatas.players?.get(0) ?: Player("",0,"opponent")

            matchProcessUiState = MatchProcessUiState.Success(player)
        }

    }
}