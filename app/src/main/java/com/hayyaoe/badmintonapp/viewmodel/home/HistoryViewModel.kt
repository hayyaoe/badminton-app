package com.hayyaoe.badmintonapp.viewmodel.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.Games
import com.hayyaoe.badmintonapp.model.History
import com.hayyaoe.badmintonapp.model.HistoryResponse
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

sealed interface HistoryUiState{
    object Loading: HistoryUiState
    data class Success (val history: HistoryResponse): HistoryUiState
    object Error: HistoryUiState

}

class HistoryViewModel: ViewModel(){
    var historyUiState: HistoryUiState by mutableStateOf(HistoryUiState.Loading)

    lateinit var history: HistoryResponse

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            try {
                history = BadmintonContainer().badmintonRepositories.getUserGames()
                historyUiState = HistoryUiState.Success(history)
            }catch (e: Exception){
                Log.d("Load History Failed", e.message.toString())
                historyUiState = HistoryUiState.Error
            }

        }
    }
}