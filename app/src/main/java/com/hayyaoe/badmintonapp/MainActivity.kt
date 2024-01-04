package com.hayyaoe.badmintonapp

import android.content.res.Configuration
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.ui.BadmintonAppRoute
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.BottomBar
import com.hayyaoe.badmintonapp.ui.views.Screen
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.ui.views.auth.RegisterView
import com.hayyaoe.badmintonapp.ui.views.find.FindMatchView
import com.hayyaoe.badmintonapp.ui.views.nav.AccountScreen
import com.hayyaoe.badmintonapp.ui.views.nav.FavoriteScreen
import com.hayyaoe.badmintonapp.ui.views.nav.HomeScreen
import com.hayyaoe.badmintonapp.ui.views.nav.ShopScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BadmintonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BadmintonAppRoute()
//
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun StartScreen() {
//    val navController = navController()
//    Scaffold(
////        topBar = {
////            TopBar("", false)
////        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .padding(it),
//            ) {
//                NavHost(navController, startDestination = Screen.Register.route) {
//                    composable(Screen.Home.route) { HomeScreen() }
//                    composable(Screen.Favorite.route) { FavoriteScreen() }
//                    composable(Screen.Shop.route) { ShopScreen() }
//                    composable(Screen.Account.route) { AccountScreen() }
//                }
//            }
//        },
//        bottomBar = {
//            Box(
//                modifier = Modifier
//                    .shadow(elevation = 24.dp, ambientColor = Color.Black)
//            ){
//                BottomBar(navController)
//            }
//
//        }
//    )
//}
//
//fun AuthScreen(){
//
//}
//
//fun SplashScreen(){
//
//}

@Preview(
    showBackground = true,
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false
)
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = false
)
@Composable
fun Preview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            BadmintonAppRoute()
        }
    }
}