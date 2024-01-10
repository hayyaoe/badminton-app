package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.dataStore
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.view.HistoryCard
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.model.Set
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.viewmodel.home.CreateMatchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMatchView(
    game: Game,
    player: Player,
    opponent: Player?,
    navController: NavController,
    createMatchViewModel: CreateMatchViewModel,
    dataStore: DataStoreManager
) {

    val sets by createMatchViewModel.setState.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
    ) {
        Scaffold(
            topBar = {
                TopBar("", true, navController)
            },
            content = { it ->
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(36.dp),
                ) {

                    LazyColumn(
                        modifier = Modifier
                    ) {

                        item {
                            Text(
                                text = "Match",
                                lineHeight = 60.sp,
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                        }
                        item {
                            player.photo?.let { it1 ->
                                if (opponent != null) {
                                    if (opponent.photo != null){
                                        HistoryCard(
                                            date = createMatchViewModel.formatDate(game.created_at),
                                            playerPict = it1,
                                            opponentPict = opponent.photo,
                                            player = player.username,
                                            opponent = opponent.username,
                                            details = game.gamecode,
                                            yourScore = game.score_1,
                                            opponentScore = game.score_2,
                                            context = context
                                        )
                                    }else{
                                        HistoryCard(
                                            date = createMatchViewModel.formatDate(game.created_at),
                                            playerPict = it1,
                                            opponentPict = "",
                                            player = player.username,
                                            opponent = opponent.username,
                                            details = game.gamecode,
                                            yourScore = game.score_1,
                                            opponentScore = game.score_2,
                                            context = context
                                        )
                                    }
                                }else{
                                    HistoryCard(
                                        date = createMatchViewModel.formatDate(game.created_at),
                                        playerPict = it1,
                                        opponentPict = "",
                                        player = player.username,
                                        opponent = "Opponent",
                                        details = game.gamecode,
                                        yourScore = game.score_1,
                                        opponentScore = game.score_2,
                                        context = context
                                    )
                                }
                            }
                        }
                        var index = 1  // Declare the index variable outside the items Composable

                        items(sets) { set ->
                            CardSetView(
                                setNo = "Set $index",
                                setData = set,
                                player = player,
                                opponent= opponent,
                                createMatchViewModel = createMatchViewModel,
                                sets = sets,
                                dataStore = dataStore
                            )
                            index++  // Increment the index for the next iteration
                        }
                        item {
                            CustomButton(onClick = { createMatchViewModel.createSet(game.id) }, content = "Add Set", isEnabled = (
                                    ((game.score_1 == 1 && game.score_2 == 1) && sets.size == 2) ||
                                            ((game.score_1 == 0 && game.score_2 == 0) && sets.size == 0) ||
                                            ((game.score_1 == 1 || game.score_2 == 1) && sets.size == 1)
                                    ),modifier = Modifier.padding(vertical = 10.dp))
                        }

                        item{
                            CustomButton(onClick = {
                                createMatchViewModel.ConfirmGame()
                                                   }, content = "Confirm Match", isEnabled = ((game.score_1 == 2 || game.score_2 ==2 ) && game.gamestatus == 0) && opponent != null , modifier = Modifier.padding(bottom = 10.dp))
                        }

                        item{
                            CustomButton(onClick = {  createMatchViewModel.isGameConfirmed(navController) }, content = "Finish Match", isEnabled = game.gamestatus == 1, modifier = Modifier.padding(bottom = 10.dp))
                        }

                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .shadow(elevation = 24.dp, ambientColor = Color.Black)
                ) {
//                    BottomBar(navController)
                }

            }
        )
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun CreateMatchPreview() {

    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            CardSetView(
//                _set_no = "set 1",
//                _name1 = "rafi 1",
//                _name2 = "rafi 2",
//                _score1 = "20",
//                _score2 = "300"
//            ){
//
//            }
//            CreateMatchView(
//                game = ,
//                player = ,
//                opponent = ,
//                navController = ,
//                createMatchViewModel =
//            )
        }
    }
}