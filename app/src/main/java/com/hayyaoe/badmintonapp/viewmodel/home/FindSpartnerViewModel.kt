package com.hayyaoe.badmintonapp.viewmodel.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import kotlinx.coroutines.launch

sealed interface FindSpartnerUiState{
    object Loading: FindSpartnerUiState
    data class Success(val people: List<OtherUser>): FindSpartnerUiState
    object Error: FindSpartnerUiState
}

class FindSpartnerViewModel: ViewModel(){
    var findSpartnerUiState: FindSpartnerUiState by mutableStateOf(FindSpartnerUiState.Loading)
    lateinit var people: List<OtherUser>


    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            try {
                people = BadmintonContainer().badmintonRepositories.get_users()
                findSpartnerUiState = FindSpartnerUiState.Success(people)

            }catch (e: Exception){
                Log.d("Find Spartner Load Data", e.message.toString())
                findSpartnerUiState = FindSpartnerUiState.Error
            }



        }
    }

    fun getLocationById(locationId: Int): String{

        var regions = listOf<Location>()
        viewModelScope.launch{
            try {
                regions = BadmintonContainer().badmintonRepositories.all_locations()
            }catch (e: Exception){
                Log.d("Regions In Spartner Load Data",e.message.toString())
            }
        }
        var location = ""
        for (region in regions){
            Log.d("RegionId","$region.id")
            if (region.id == locationId){
                location = region.region
                Log.d("RegionMatch",location)
            }
        }
        return location
    }
}