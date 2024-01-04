package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch

sealed interface HomeUiState{
    data class Success(val user : User): HomeUiState
    object Loading: HomeUiState
    object Error: HomeUiState
}

class HomeViewModel: ViewModel(){

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)


    fun findSpartner(
        navController:NavController
    ){
        navController.navigate("")
    }
    fun createMatch(
        navController:NavController
    ){
        navController.navigate("")
    }

    fun joinMatch(
        navController:NavController
    ){
        navController.navigate("")
    }

    fun matchHistory(
        navController:NavController
    ){
        navController.navigate("")
    }
}