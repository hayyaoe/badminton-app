package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.GetGameData
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.ui.views.auth.poppins
import com.hayyaoe.badmintonapp.viewmodel.home.MatchProcessViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchProcessView(
    navController: NavController,
    userData: Player,
    matchProcessViewModel: MatchProcessViewModel,
    game: GetGameData,
){
    val context = LocalContext.current

    var showDialog by remember{ mutableStateOf(false) }

    @Composable
    fun ScorePopup(){
        Box (contentAlignment = Alignment.Center){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        showDialog = false
                    }
            )
            ScorePopUp(
                    game = game,
                    onClick1 = {
                        showDialog = false
                        matchProcessViewModel.ConfirmGame(navController)
                    },
                    onClick2 = {
                        showDialog = false
                        matchProcessViewModel.DeclineGame()
                    },
                    matchProcessViewModel = matchProcessViewModel
                )
        }
    }

    Box (
        modifier = Modifier
    ){
        Scaffold (
            topBar = {
                TopBar(title = "", navController =navController, navIcon = true)
            },
            content = {
                Column (
                    Modifier
                        .padding(it)
                        .padding(12.dp)
                ) {
                    LazyColumn(modifier = Modifier){
                        item {
                            Text(
                                text = "Match \nProcess",
                                lineHeight = 60.sp,
                                fontSize = 52.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppins,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                        item {
                            PlayerCard(userData,context)
                        }
                        item {
                            CustomButton(onClick = { showDialog = matchProcessViewModel.isGameConfirmed() }, content = "Confirm Match", modifier = Modifier.padding(top = 30.dp),true)
                        }
                    }
                }
            }
        )

        if (showDialog) {
            Dialog(
                onDismissRequest = {
                    showDialog = false
                }
            ) {
                ScorePopup()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun matchProcessPreview(){
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            MatchProcessView(navController(),  Player("", 1,"BUDI"),viewModel())
        }
    }
}