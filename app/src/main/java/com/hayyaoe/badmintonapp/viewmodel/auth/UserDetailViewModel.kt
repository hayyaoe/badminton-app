package com.hayyaoe.badmintonapp.viewmodel.auth

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.UpdateUser
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.model.UserRegistrationRequest
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.repository.BadmintonContainer.Companion.ACCESS_TOKEN
import com.hayyaoe.badmintonapp.repository.BadmintonContainer.Companion.EMAIL
import com.hayyaoe.badmintonapp.repository.BadmintonRepositories
import com.hayyaoe.badmintonapp.ui.ListScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

sealed interface UserDetailUiState{
    data class Success(
        val regions: List<String>
    ): UserDetailUiState
    object Error: UserDetailUiState
    object Loading: UserDetailUiState
}

class UserDetailViewModel : ViewModel() {

    var userDetailUiState: UserDetailUiState by mutableStateOf(UserDetailUiState.Loading)
    private val _uiState = MutableStateFlow(User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()

    private val _imageUploadResponse = MutableLiveData<String>()
    val imageUploadResponse: LiveData<String> get() = _imageUploadResponse

    private lateinit var locations: List<Location>
    private val regions: MutableList<String> = mutableListOf() // Initialize the regions list

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                locations = BadmintonContainer().badmintonRepositories.all_locations()
                for (location in locations) {
                    regions.add(location.region)
                }
                userDetailUiState = UserDetailUiState.Success(regions)
            } catch (e: Exception) {
                Log.d("NetworkTest", e.message.toString())
                userDetailUiState = UserDetailUiState.Error
            }
        }
    }


    @SuppressLint("Recycle")
    fun uploadImage(imageUri: Uri, context: Context){
        viewModelScope.launch {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val requestFile = inputStream?.readBytes()?.toRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("image", "filename", requestFile!!)

            val response = BadmintonContainer().badmintonRepositories.upload_picture(body)
            Log.d("IMAGE PATH", response)
            _imageUploadResponse.value = response
            BadmintonContainer().badmintonRepositories.update_profile_picture(EMAIL,response)
        }
    }

    fun updateUser(
        contacts: String,
        phone:String,
        region: String,
        navController: NavController,
        dataStore: DataStoreManager
    ){
            viewModelScope.launch {
                var location_id = 0
                val lokasi = BadmintonContainer().badmintonRepositories.all_locations()
                for (location in lokasi) {
                    // Log each location for debugging
                    Log.d("Location", "Region: ${location.region}, ID: ${location.id}")

                    if (region.equals(location.region, true)) {
                        location_id = location.id
                    }
                }
                Log.d("Final Location ID", location_id.toString())
                Log.d("EMAIL", EMAIL)
                Log.d("TOKEN", ACCESS_TOKEN)
                Log.d("Location_ID", "$location_id")
                val userData = BadmintonContainer().badmintonRepositories.get_user()
                Log.d("User Data", userData.toString())
                try {
                    val user = UpdateUser(
                        username = userData.username,
                        email = userData.email,
                        password = null,
                        phone_number = phone,
                        contacts = contacts,
                        location_id = location_id,
                        rank = userData.rank,
                        image_path = if (_imageUploadResponse.value.isNullOrBlank()) null else _imageUploadResponse.value
                    )
                    BadmintonContainer().badmintonRepositories.update_user(user)
                    navController.navigate("Home"){
                        popUpTo("auth"){
                            inclusive = true
                        }
                    }

                } catch (e: Exception) {
                    Log.d("Update Error", e.message.toString())
                    // Handle error state, update UI, etc.
                    userDetailUiState = UserDetailUiState.Error
                }
            }

    }
}
