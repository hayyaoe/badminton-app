package com.hayyaoe.badmintonapp.ui

import android.annotation.SuppressLint
import android.content.res.AssetManager
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
import com.hayyaoe.badmintonapp.model.People
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.view.HistoryView
import com.hayyaoe.badmintonapp.ui.view.HomeView
import com.hayyaoe.badmintonapp.ui.view.JoinMatchView
import com.hayyaoe.badmintonapp.ui.view.SettingsView
import com.hayyaoe.badmintonapp.ui.views.auth.LoginView
import com.hayyaoe.badmintonapp.ui.views.auth.RegisterView
import com.hayyaoe.badmintonapp.ui.views.auth.UserDetailsView
import com.hayyaoe.badmintonapp.ui.views.find.FindSpartnerView
import com.hayyaoe.badmintonapp.ui.views.match.CommentView
import com.hayyaoe.badmintonapp.ui.views.match.CreateMatchView
import com.hayyaoe.badmintonapp.ui.views.match.MatchProcessView
import com.hayyaoe.badmintonapp.viewmodel.home.HomeViewModel
import com.hayyaoe.badmintonapp.viewmodel.auth.LoginUiState
import com.hayyaoe.badmintonapp.viewmodel.auth.LoginViewModel
import com.hayyaoe.badmintonapp.viewmodel.auth.RegisterUiState
import com.hayyaoe.badmintonapp.viewmodel.auth.RegisterViewModel
import com.hayyaoe.badmintonapp.viewmodel.auth.UserDetailUiState
import com.hayyaoe.badmintonapp.viewmodel.auth.UserDetailViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.CommentUiState
import com.hayyaoe.badmintonapp.viewmodel.home.CommentViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.CreateMatchUiState
import com.hayyaoe.badmintonapp.viewmodel.home.CreateMatchViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.FindSpartnerUiState
import com.hayyaoe.badmintonapp.viewmodel.home.FindSpartnerViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.HistoryUiState
import com.hayyaoe.badmintonapp.viewmodel.home.HistoryViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.HomeUiState
import com.hayyaoe.badmintonapp.viewmodel.home.JoinMatchUiState
import com.hayyaoe.badmintonapp.viewmodel.home.JoinMatchViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.MatchProcessUiState
import com.hayyaoe.badmintonapp.viewmodel.home.MatchProcessViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.SettingsUiState
import com.hayyaoe.badmintonapp.viewmodel.home.SettingsViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

enum class ListScreen{
    RegisterView,
    UserDetailsView,
    LoginView,
    HomeView,
    SettingsView,
    FindSpartnerView,
    HistoryView,
    JoinMatchView,
    CreateMatchView,
    MatchProcessView,
    CommentView,
}

@OptIn(ExperimentalMaterial3Api::class)
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
                        when (val status = homeViewModel.homeUiState) {
                            is HomeUiState.Loading -> {}
                            is HomeUiState.Error -> {}
                            is HomeUiState.Success -> HomeView(
                                homeViewModel = homeViewModel,
                                navController = navController,
                                user = status.user
                            )
                        }
                    }
                }

                composable(ListScreen.HistoryView.name){
                    val historyViewModel: HistoryViewModel = viewModel()
                    when (val status = historyViewModel.historyUiState) {
                        is HistoryUiState.Loading -> {}
                        is HistoryUiState.Error -> {}
                        is HistoryUiState.Success -> HistoryView(
                            historyViewModel = historyViewModel,
                            navController = navController,
                            historyList = status.history
                        )
                    }
                }


                composable(ListScreen.FindSpartnerView.name){
                    val findSpartnerViewModel: FindSpartnerViewModel = viewModel()

                    when (val status = findSpartnerViewModel.findSpartnerUiState) {
                        is FindSpartnerUiState.Loading -> {}
                        is FindSpartnerUiState.Error -> {}
                        is FindSpartnerUiState.Success -> FindSpartnerView(
                            findSpartnerViewModel = findSpartnerViewModel,
                            navController = navController,
                            people = status.people
                        )
                    }
                }

                composable(ListScreen.CreateMatchView.name){
                    val createMatchViewModel : CreateMatchViewModel = viewModel()

                    when (val status = createMatchViewModel.createMatchUiState){
                        is CreateMatchUiState.Loading->{}
                        is CreateMatchUiState.Error->{}
                        is CreateMatchUiState.Success-> CreateMatchView(
                            game = status.game,
                            player = status.player,
                            opponent = status.opponent,
                            navController = navController,
                            createMatchViewModel = createMatchViewModel,
                            dataStore = dataStore
                        )
                    }
                }

                composable(ListScreen.SettingsView.name){
                    val settingsViewModel : SettingsViewModel = viewModel()

                    when (val status = settingsViewModel.settingsUiState){
                        is SettingsUiState.Loading->{Log.d("loading", ListScreen.SettingsView.name)}
                        is SettingsUiState.Error->{Log.d("error", ListScreen.SettingsView.name)}
                        is SettingsUiState.Success-> {
                            SettingsView(
                                settingsViewModel = settingsViewModel,
                                navController = navController,
                                dataStore = dataStore,
                                status.userData,
                                status.regions
                            )
                            Log.d("success", ListScreen.SettingsView.name)
                        }
                    }
                }

                composable(ListScreen.JoinMatchView.name){
                    val joinMatchViewModel: JoinMatchViewModel = viewModel()

                    when(val status = joinMatchViewModel.joinMatchUiState){
                        is JoinMatchUiState.Loading->{}
                        is JoinMatchUiState.Error->{}
                        is JoinMatchUiState.Success->{
                            JoinMatchView(joinMatchViewModel,navController, dataStore)
                        }
                    }
                }
                
                composable(ListScreen.MatchProcessView.name){
                    val matchProcessViewModel : MatchProcessViewModel = viewModel()

                    when(val status = matchProcessViewModel.matchProcessUiState){
                        is MatchProcessUiState.Loading->{}
                        is MatchProcessUiState.Error->{}
                        is MatchProcessUiState.Success->{
                            MatchProcessView(navController, status.userData, matchProcessViewModel, status.game)
                        }
                    }
                }

                composable(ListScreen.CommentView.name){
                    val commentViewModel : CommentViewModel = viewModel()

                    when(val status = commentViewModel.commentUiState){
                        is CommentUiState.Loading->{}
                        is CommentUiState.Error->{}
                        is CommentUiState.Success->{
                            CommentView(navController = navController, commentViewModel = commentViewModel, opponent = status.opponent, game = status.game, players = status.opponent)
                        }
                    }
                }
            }
        }
    }
}




