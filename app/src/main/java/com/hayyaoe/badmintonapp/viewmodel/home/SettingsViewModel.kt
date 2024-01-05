package com.hayyaoe.badmintonapp.viewmodel.home

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayyaoe.badmintonapp.model.Location
import com.hayyaoe.badmintonapp.model.UpdateUser
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.repository.BadmintonContainer.Companion.EMAIL
import com.hayyaoe.badmintonapp.ui.BadmintonAppRoute
import com.hayyaoe.badmintonapp.viewmodel.auth.UserDetailUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import kotlin.math.log

sealed interface SettingsUiState{
    object Loading: SettingsUiState
    data class Success(val userData: UserData, val regions: List<String>): SettingsUiState
    object Error: SettingsUiState
}

class SettingsViewModel : ViewModel(){
    var settingsUiState : SettingsUiState by mutableStateOf(SettingsUiState.Loading)
    private val _imageUploadResponse = MutableLiveData<String>()
    val imageUploadResponse: LiveData<String> get() = _imageUploadResponse


    private lateinit var userData: UserData
    private lateinit var locations: List<Location>
    private var user: UserData = UserData()
    private val regions: MutableList<String> = mutableListOf()
    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            try {
                userData = BadmintonContainer().badmintonRepositories.get_user()
                Log.d("User Data", userData.toString())
                user = userData

            }catch (e: Exception){
                Log.d("User Settings Error", e.message.toString())
                settingsUiState = SettingsUiState.Error
            }
            try {
                locations = BadmintonContainer().badmintonRepositories.all_locations()
                for (location in locations) {
                    regions.add(location.region)
                }
            } catch (e: Exception) {
                Log.d("NetworkTest", e.message.toString())
                settingsUiState = SettingsUiState.Error
            }
            settingsUiState = SettingsUiState.Success(user,regions)

        }

    }

    fun isPasswordCorrect(password1: String, password2: String): Boolean {
        if (password1.isNotBlank() && password2.isNotBlank()) {
            return password1 != password2

        }
        return false
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


    fun updateUser(username: String, region: String, phone_number: String, contacts: String, password: String?, rank: Int,image_path: String?){
        viewModelScope.launch {
            var location_id = 0
            for (location in locations) {
                // Log each location for debugging
                Log.d("Location", "Region: ${location.region}, ID: ${location.id}")

                if (region.equals(location.region, true)) {
                    location_id = location.id
                }
            }
            try {
                val user = UpdateUser(
                    username = username,
                    email = BadmintonContainer.EMAIL,
                    password = if (password.isNullOrBlank()) null else password,
                    phone_number = phone_number,
                    contacts = contacts,
                    location_id = location_id,
                    rank = rank,
                    image_path = image_path
                )
                user.image_path = _imageUploadResponse.value
                val result = BadmintonContainer().badmintonRepositories.update_user(user)
                Log.d("SETTING SAVE SUCCESS", result.toString())
                settingsUiState = SettingsUiState.Success(result,regions)

            }catch (e: Exception){
                Log.d("SETTING SAVE ERROR", e.message.toString())
                settingsUiState = SettingsUiState.Error
            }

        }
    }
}