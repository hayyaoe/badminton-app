package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.ListScreen
import kotlinx.coroutines.launch

sealed interface JoinMatchUiState{
    object Success : JoinMatchUiState
    object Loading : JoinMatchUiState
    object Error : JoinMatchUiState
}

class JoinMatchViewModel : ViewModel (){
    var joinMatchUiState: JoinMatchUiState by mutableStateOf(JoinMatchUiState.Success)

    fun joinMatch(gameCode: String, navController: NavController, dataStore: DataStoreManager){
        viewModelScope.launch {
            val gameData = BadmintonContainer().badmintonRepositories.join_game(gameCode)

            if (gameData.game_id != 0){
            dataStore.saveGameCode(gameCode)

                navController.navigate(ListScreen.MatchProcessView.name)

                dataStore.getGameCode.collect{gamecode->
                    if (gamecode !=null){
                        BadmintonContainer.GAMECODE = gamecode
                    }
                }
            }
        }

    }
}