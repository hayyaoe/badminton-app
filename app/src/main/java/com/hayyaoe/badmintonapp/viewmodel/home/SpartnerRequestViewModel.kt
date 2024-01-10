package com.hayyaoe.badmintonapp.viewmodel.home


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.model.SpartnerRequest
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch

sealed interface SpartnerRequestUiState{
    object Loading: SpartnerRequestUiState
    data class Success(val people: List<SpartnerRequest>): SpartnerRequestUiState
    object Error: SpartnerRequestUiState
}

class SpartnerRequestViewModel: ViewModel(){
    var spartnerRequestUiState: SpartnerRequestUiState by mutableStateOf(SpartnerRequestUiState.Loading)
    lateinit var people: List<SpartnerRequest>

    var regions = listOf<Location>()

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            try {
                val user = BadmintonContainer().badmintonRepositories.get_user()
                val request = BadmintonContainer().badmintonRepositories.get_spartner_requests(user.id)
                people = request.spartnerRequests
                spartnerRequestUiState = SpartnerRequestUiState.Success(people)
                regions = BadmintonContainer().badmintonRepositories.all_locations()

            }catch (e: Exception){
                Log.d("Find Spartner Load Data", e.message.toString())
                spartnerRequestUiState = SpartnerRequestUiState.Error
            }
        }
    }

    fun getLocationById(locationId: Int): String{
        var location = ""
        for (region in regions){
            if (region.id == locationId){
                location = region.region
                Log.d("RegionMatch",location)
            }
        }
        return location
    }

    fun addFriend(otherUserId: Int){
        viewModelScope.launch {
            val user = BadmintonContainer().badmintonRepositories.get_user()
            val data = BadmintonContainer().badmintonRepositories.create_spartner(user.id, otherUserId)
            if (data.message == "Spartner Created"){
                spartnerRequestUiState = SpartnerRequestUiState.Success(people)
            }
        }
    }

    fun spartnerUpdate(game_id: Int){
        viewModelScope.launch{
            val user = BadmintonContainer().badmintonRepositories.get_user()
            BadmintonContainer().badmintonRepositories.update_spartner(game_id)
            val request = BadmintonContainer().badmintonRepositories.get_spartner_requests(user.id)
            people = request.spartnerRequests
            spartnerRequestUiState = SpartnerRequestUiState.Success(people)
        }

    }
}