package com.hayyaoe.badmintonapp.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

sealed interface HistoryUiState{
    object Loading: HistoryUiState
    object Success: HistoryUiState
    object Error: HistoryUiState

}

class HistoryViewModel: ViewModel(){
    var historyUiState: HistoryUiState by mutableStateOf(HistoryUiState.Success)
}