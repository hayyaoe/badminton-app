package com.hayyaoe.badmintonapp.viewmodel.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.model.APIResponse
import com.hayyaoe.badmintonapp.model.TokenResponse
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.model.UserDataResponse
import com.hayyaoe.badmintonapp.model.UserRegistrationRequest
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.repository.BadmintonRepositories
import com.hayyaoe.badmintonapp.service.BadmintonDBServices
import com.hayyaoe.badmintonapp.ui.ListScreen
//import com.hayyaoe.badmintonapp.ui.ListScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface RegisterUiState{
    object Success: RegisterUiState
    object Error: RegisterUiState
    object Loading: RegisterUiState
}
class RegisterViewModel : ViewModel() {

    var registerUiState: RegisterUiState by mutableStateOf(RegisterUiState.Success)
    private val _uiState = MutableStateFlow<List<User>>(emptyList())
    val uiState: StateFlow<List<User>> = _uiState.asStateFlow()

    fun registerButton(
        password: String,
        email: String,
        username: String,
        rank: String,
        navController: NavController,
        dataStore: DataStoreManager
    ) {
            if (username.isNotBlank() && password.isNotBlank() && email.isNotBlank() && rank.isNotBlank()) {
                val user = UserRegistrationRequest(
                    username = username,
                    password = password,
                    email = email,
                    rank = if (rank == "Advanced") 1200 else if (rank == "Intermediate") 800 else 400,
                    contacts = "",
                    phone_number = ""
                )

                viewModelScope.launch {

                    try {
                        val response = BadmintonContainer().badmintonRepositories.registerUser(user)

                        // Check if response.data is a list and contains at least two elements
                        val data = response.data as List<*>
                        Log.d("register response", response.data.toString())

                            dataStore.saveToken(data[1] as String)
                            dataStore.saveEmail(data[0] as String)

                        navController.navigate(ListScreen.UserDetailsView.name){
                            popUpTo("auth"){
                                inclusive = true
                            }
                        }
                        dataStore.getEmail.collect{email->
                            if (email !=null){
                                BadmintonContainer.EMAIL = email
                            }
                        }
                        Log.d("Email", BadmintonContainer.EMAIL)
                            dataStore.getToken.collect {token ->
                                if (token != null) {
                                    BadmintonContainer.ACCESS_TOKEN = token
                                }
                            }


                    } catch (e: Exception) {
                        Log.d("Register Error", e.message.toString())
                        // Handle error state, update UI, etc.
                        registerUiState = RegisterUiState.Error
                    }
                }
            } else {
                return
            }
    }


    fun isPasswordCorrect(password1: String, password2: String): Boolean {
        if (password1.isNotBlank() && password2.isNotBlank()) {
            return password1 != password2

        }
        return false
    }

    fun alreadyHaveAnAccount(navController: NavController){
        navController.navigate(ListScreen.LoginView.name){
            popUpTo("auth")
        }
    }


//    fun isEmail(email: String):Boolean{
//
//        var isError = false
//        if (email.isNotBlank() && email.isNotEmpty()) {
//            viewModelScope.launch{
//                try {
//                    isError = BadmintonContainer().badmintonRepositories.email_check(email).isValid
//                }catch(e: Exception){
//                    Log.d("NetworkTest", e.message.toString())
//                    registerUiState = RegisterUiState.Error
//                }
//            }
//        }
//
//        return isError
//    }

}
