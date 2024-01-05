package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.GameData
import com.hayyaoe.badmintonapp.model.Set
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

sealed interface CreateMatchUiState{
    object Loading: CreateMatchUiState
    data class Success(val game: Game, val set1: Set, val set2: Set, val set3: Set?, val player: UserData, val opponent: UserData?): CreateMatchUiState
    object Error: CreateMatchUiState
}

class CreateMatchViewModel : ViewModel(){
    var createMatchUiState: CreateMatchUiState by mutableStateOf(CreateMatchUiState.Loading)

    lateinit var game: Game
    lateinit var set1: Set
    lateinit var set2: Set
    var set3: Set? = null
    lateinit var user1: UserData
    var user2: UserData? = null

    init {
        loadData()
    }

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }

    fun loadData(){
        viewModelScope.launch {
            val gameData = BadmintonContainer().badmintonRepositories.create_game()
            game = gameData.game
            set1 =gameData.set1
            set2 =gameData.set2
            set3 =gameData.set3
            user1 = gameData.user1
            user2 = gameData.user2
            createMatchUiState = CreateMatchUiState.Success(game,set1,set2,set3,user1,user2)
        }

    }



}