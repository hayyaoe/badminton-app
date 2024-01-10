package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.model.GetGameData
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.ui.views.auth.poppins
import com.hayyaoe.badmintonapp.viewmodel.home.CommentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentView(
    commentViewModel: CommentViewModel,
    navController: NavController,
    opponent: Player,
    game: GetGameData,
    players: Player,
){
    var comment by rememberSaveable { mutableStateOf("Comment Their Play") }

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
                                text = "Comment",
                                lineHeight = 60.sp,
                                fontSize = 52.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppins,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                        item {
                            CommentCard(opponent = opponent , onValueChange = {comment = it}, value = comment, )
                        }
                        item {
                            CustomButton(onClick = { commentViewModel.postComment(players.user_id, game.game_id, comment = comment, navController) }, content = "Submit", modifier = Modifier.padding(top = 30.dp), comment.isNotBlank())
                        }
                    }
                }
            }
        )
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
private fun CommentPreview(){

    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            CommentView()
        }
    }
}