package com.hayyaoe.badmintonapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.view.HomeView
import com.hayyaoe.badmintonapp.ui.views.auth.LoginView
import com.hayyaoe.badmintonapp.ui.views.auth.RegisterView
import com.hayyaoe.badmintonapp.ui.views.auth.UserDetailsView
import com.hayyaoe.badmintonapp.viewmodel.home.HomeViewModel
import com.hayyaoe.badmintonapp.viewmodel.auth.LoginUiState
import com.hayyaoe.badmintonapp.viewmodel.auth.LoginViewModel
import com.hayyaoe.badmintonapp.viewmodel.auth.RegisterUiState
import com.hayyaoe.badmintonapp.viewmodel.auth.RegisterViewModel
import com.hayyaoe.badmintonapp.viewmodel.auth.UserDetailUiState
import com.hayyaoe.badmintonapp.viewmodel.auth.UserDetailViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

enum class ListScreen(){
    RegisterView,
    UserDetailsView,
    LoginView,
    HomeView,
    SettingsView,
    FindMatchView
}

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BadmintonAppRoute() {
    val navController = rememberNavController()
    val dataStore = DataStoreManager(LocalContext.current)

    LaunchedEffect(Unit) {

        coroutineScope {
            Log.d("LaunchedEffect", "LaunchedEffect triggered!")
            launch {
                dataStore.getEmail.collect { email ->
                    if (email != null) {
                        BadmintonContainer.EMAIL = email
                    }
                    Log.d("EMAIL ON LAUNCH", BadmintonContainer.EMAIL)
                }
            }
            launch {
                dataStore.getToken.collect { token ->
                    if (token != null) {
                        BadmintonContainer.ACCESS_TOKEN = token
                    }
                    Log.d("TOKEN ON LAUNCH", BadmintonContainer.ACCESS_TOKEN)
                }
            }
        }
    }


    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(
                startDestination = ListScreen.RegisterView.name,
                route = "auth"
            ) {
                composable(ListScreen.RegisterView.name) {
                    if (BadmintonContainer.ACCESS_TOKEN.isEmpty()) {
                        val registerViewModel: RegisterViewModel = viewModel()

                        when (val status = registerViewModel.registerUiState) {
                            is RegisterUiState.Loading -> {
                                Log.d("loading", ListScreen.RegisterView.name)
                            }

                            is RegisterUiState.Success -> {
                                RegisterView(
                                    registerViewModel = registerViewModel,
                                    navController = navController,
                                    dataStore = dataStore
                                )
                                Log.d("success", ListScreen.RegisterView.name)
                            }

                            is RegisterUiState.Error -> {
                                Log.d("error", ListScreen.RegisterView.name)
                            }
                        }
                    } else {
                        navController.navigate("home"){
                            popUpTo("auth"){
                                inclusive = true
                            }
                        }
                    }
                }

                composable(ListScreen.UserDetailsView.name) {

                    val userDetailViewModel: UserDetailViewModel = viewModel()
                    when (val status = userDetailViewModel.userDetailUiState) {
                        is UserDetailUiState.Loading -> {}
                        is UserDetailUiState.Success -> UserDetailsView(
                            userDetailViewModel = userDetailViewModel,
                            navController = navController,
                            dataStore = dataStore,
                            regions = status.regions
                        )

                        is UserDetailUiState.Error -> {}

                    }
                }

                composable(ListScreen.LoginView.name) {
                    if (BadmintonContainer.ACCESS_TOKEN.isEmpty()) {
                        val loginViewModel: LoginViewModel = viewModel()
                        when (val status = loginViewModel.loginUiState) {
                            is LoginUiState.Loading -> {}
                            is LoginUiState.Error -> {}
                            is LoginUiState.Success -> LoginView(
                                loginViewModel = loginViewModel,
                                navController = navController,
                                dataStore = dataStore
                            )
                        }
                    } else {
                        navController.navigate("home"){
                            popUpTo("auth"){
                                inclusive = true
                            }
                        }
                    }
                }
            }

            navigation(
                startDestination = ListScreen.HomeView.name,
                route="home"
            ) {
                composable(ListScreen.HomeView.name) {
                    Log.d("ACCESS TOKEN ON HOME VIEW", BadmintonContainer.ACCESS_TOKEN)
                    if (BadmintonContainer.ACCESS_TOKEN.isEmpty()) {
                        navController.navigate("auth"){
                            popUpTo("home"){
                                inclusive=true
                            }
                        }
                    } else {

                        val homeViewModel: HomeViewModel = viewModel()
                        HomeView(
                            homeViewModel = homeViewModel,
                            navController = navController
                        )

                    }
                }
            }
        }
    }
}




