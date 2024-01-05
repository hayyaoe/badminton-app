package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

sealed interface JoinMatchUiState{
    object Success : JoinMatchUiState
    object Loading : JoinMatchUiState
    object Error : JoinMatchUiState
}

class JoinMatchViewModel : ViewModel (){
    var joinMatchUiState: JoinMatchUiState by mutableStateOf(JoinMatchUiState.Success)
}