package com.hayyaoe.badmintonapp.viewmodel.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.Spartner
import com.hayyaoe.badmintonapp.model.SpartnerRequest
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch

sealed interface SpartnersUiState{
    object Loading: SpartnersUiState
    data class Success(val people: List<Spartner>): SpartnersUiState
    object Error: SpartnersUiState
}

class SpartnersViewModel: ViewModel() {
    var spartnerUiState: SpartnersUiState by mutableStateOf(SpartnersUiState.Loading)
    lateinit var people: List<Spartner>
    var regions = listOf<Location>()

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            try {
                val user = BadmintonContainer().badmintonRepositories.get_user()
                val request = BadmintonContainer().badmintonRepositories.get_spartners(user.id)
                people = request.spartner
                spartnerUiState = SpartnersUiState.Success(people)
                regions = BadmintonContainer().badmintonRepositories.all_locations()

            }catch (e: Exception){
                Log.d("Spartners Load Data", e.message.toString())
                spartnerUiState = SpartnersUiState.Error
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
}