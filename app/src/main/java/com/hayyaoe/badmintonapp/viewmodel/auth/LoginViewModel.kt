package com.hayyaoe.badmintonapp.viewmodel.auth

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.model.User
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.ListScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed  interface LoginUiState{
    object Loading :LoginUiState
    object Success : LoginUiState
    object Error: LoginUiState
}

class LoginViewModel : ViewModel() {
    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Success)
    private val _uiState = MutableStateFlow<User>(User())
    val uiState : StateFlow<User> = _uiState.asStateFlow()

    fun login(email: String, password: String, navController: NavController, dataStore: DataStoreManager){
        viewModelScope.launch {
            try {
                val response = BadmintonContainer().badmintonRepositories.login(email,password)
                if (response.message.equals("Incorrect Password", true)){
                    navController.navigate(ListScreen.LoginView.name){
                        popUpTo(ListScreen.LoginView.name){inclusive = true}
                    }
                }else if (response.message.equals("User Not Found", true)){
                    navController.navigate(ListScreen.LoginView.name){
                        popUpTo(ListScreen.LoginView.name){inclusive = true}
                    }
                }else{
                    val data = response.data as List<*>
                    Log.d("login response", response.data.toString())

                    dataStore.saveToken(data[0] as String)
                    dataStore.saveEmail(data[1] as String)

                    navController.navigate(ListScreen.HomeView.name)

                    dataStore.getToken.collect{
                            token->
                        if (token != null){
                            BadmintonContainer.ACCESS_TOKEN = token
                        }
                    }
                    dataStore.getEmail.collect{
                            email->
                        if (email != null){
                            BadmintonContainer.EMAIL = email
                        }
                    }

                }
            } catch (e: Exception){
                Log.d("Login Error",e.message.toString())
                loginUiState = LoginUiState.Error
            }


        }
    }
}

