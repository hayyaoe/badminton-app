package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.view.HistoryCard
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.model.Set
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.viewmodel.home.CreateMatchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateMatchView(
    game: Game,
    player: UserData,
    opponent: UserData?,
    set1: Set,
    set2: Set,
    set3: Set?,
    navController: NavController,
    createMatchViewModel: CreateMatchViewModel
) {

    val context = LocalContext.current

    var gameScore1 by remember { mutableStateOf(game.score_1) }
    var gameScore2 by remember { mutableStateOf(game.score_2) }
    var set1score1 by remember { mutableStateOf(set1.player1_score.toString()) }
    var set1score2 by remember { mutableStateOf(set1.player2_score.toString()) }
    var set2score1 by remember { mutableStateOf(set2.player1_score.toString()) }
    var set2score2 by remember { mutableStateOf(set2.player2_score.toString()) }


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
                            player.image_path?.let { it1 ->
                                if (opponent != null) {
                                    opponent.image_path?.let { it2 ->
                                        HistoryCard(
                                            date = createMatchViewModel.formatDate(game.created_at),
                                            playerPict = it1,
                                            opponentPict = it2,
                                            player = player.username,
                                            opponent = opponent.username,
                                            details = game.gamecode,
                                            yourScore = gameScore1,
                                            opponentScore = gameScore2,
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
                        item{

                                CardSetView(
                                    _set_no = "set 1",
                                    _name1 = player.username,
                                    _name2 = opponent?.username ?: "Opponent",
                                    _score1 = set1score1,
                                    _score2 = set1score2,
                                    onClick1 = {set1score1 = it},
                                    onClick2 = {set1score2 = it}
                                )
                                CardSetView(
                                    _set_no = "set 2",
                                    _name1 = player.username,
                                    _name2 = opponent?.username ?: "Opponent",
                                    _score1 = set2score1,
                                    _score2 = set2score2,
                                    onClick1 = {
                                        set2score1 = it
                                               },
                                    onClick2 = {set2score2= it}
                                )

                            if (gameScore1 ==1 && gameScore2 ==1 && set3!= null){
                                var set3score1 by remember   { mutableStateOf(set3.player1_score.toString())  }
                                var set3score2 by remember { mutableStateOf(set3.player2_score.toString())  }
                                if(set3score1.isNotBlank()&& set3score2.isNotBlank()){
                                    if(set3score1.toInt() > set3score2.toInt()){
                                        gameScore1+=1
                                    }else{
                                        gameScore2+=1
                                    }
                                }
                                CardSetView(
                                    _set_no = "set 3",
                                    _name1 = player.username,
                                    _name2 = opponent?.username ?: "Opponent",
                                    _score1 = set3score1,
                                    _score2 = set3score2,
                                    onClick1 = {set3score1 = it},
                                    onClick2 = {set3score2 = it}
                                )
                            }
                        }
                        item {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, end = 24.dp),
                                contentAlignment = Alignment.CenterEnd,
                            ){
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp),
                                    shape = MaterialTheme.shapes.medium,
                                    colors = ButtonDefaults.buttonColors(
                                        Color(0xff5DA118),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        text = "Comfirm Match",
                                        fontSize = 24.sp,
                                    )
                                }
                            }
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
        }
    }
}