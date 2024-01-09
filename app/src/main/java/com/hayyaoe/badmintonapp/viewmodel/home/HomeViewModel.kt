package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.ListScreen
import kotlinx.coroutines.launch

sealed interface HomeUiState{
    data class Success(val user : UserData): HomeUiState
    object Loading: HomeUiState
    object Error: HomeUiState
}

class HomeViewModel: ViewModel(){

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)

    lateinit var user: UserData

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            user = BadmintonContainer().badmintonRepositories.get_user()
            homeUiState = HomeUiState.Success(user)
        }
    }

    fun findSpartner(
        navController:NavController
    ){
        navController.navigate(ListScreen.FindSpartnerView.name)
    }
    fun createMatch(
        navController:NavController
    ){
        navController.navigate(ListScreen.CreateMatchView.name)
    }

    fun joinMatch(
        navController:NavController
    ){
        navController.navigate(ListScreen.JoinMatchView.name)
    }

    fun matchHistory(
        navController:NavController
    ){
        navController.navigate(ListScreen.HistoryView.name)
    }
}