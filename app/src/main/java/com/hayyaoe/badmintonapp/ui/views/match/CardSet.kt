package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.model.Set
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.auth.poppins
import com.hayyaoe.badmintonapp.viewmodel.home.CreateMatchUiState
import com.hayyaoe.badmintonapp.viewmodel.home.CreateMatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetView(
    setNo : String,
    setData: Set,
    player: Player,
    opponent: Player?,
    sets: List<Set>,
    createMatchViewModel: CreateMatchViewModel,
) {

    var score1 by remember {mutableStateOf(setData.player1_score.toString()) }
    var score2 by remember {mutableStateOf(setData.player2_score.toString()) }

    Card(
        onClick = { createMatchViewModel.updateSet(set_id = setData.id, score1,score2, sets)},
        modifier = Modifier
            .padding(4.dp, 12.dp, 4.dp, 12.dp),
        shape = RoundedCornerShape(15),
        colors = CardDefaults.cardColors(if (isSystemInDarkTheme()) Color(0xFF292828) else Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = setNo,
                        color = Color(0xff5DA118),
                        fontSize = 20.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        softWrap = true,
                        maxLines = 1,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp, 12.dp, 0.dp, 0.dp),
                    )
                    Box(
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "VS",
                            color = Color(0xff5DA118),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            fontFamily = poppins,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = 10.dp),
                        )
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column(
                                modifier = Modifier
                                    .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                    .width(64.dp)
                                    ,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = player.username,
                                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black,
                                    softWrap = true,
                                    fontFamily= poppins,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 4.dp),
                                )
                                Box(
                                    modifier = Modifier
                                        .height(64.dp)
                                        .aspectRatio(1 / 1f)
                                        .clip(RoundedCornerShape(25))
                                        .background(Color(0xff5DA118)),
                                    contentAlignment = Alignment.Center
                                ){
                                   OutlinedTextField(
                                       value = score1,
                                       onValueChange = {score1 = it},
                                       keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                       textStyle = TextStyle(
                                           color = (if (isSystemInDarkTheme()) Color.Black else Color.White),
                                           fontSize = 24.sp,
                                           fontWeight = FontWeight.Bold,
                                           textAlign = TextAlign.Center,
                                           ),
                                       singleLine = true,
                                       colors = TextFieldDefaults.outlinedTextFieldColors(disabledBorderColor = Color.Transparent, focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent),
                                   )
                                }

                            }



                            Column(
                                modifier = Modifier
                                    .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                    .width(64.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                if (opponent != null) {
                                    Text(
                                        text = opponent.username,
                                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Black,
                                        softWrap = true,
                                        fontFamily= poppins,
                                        maxLines = 1,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 4.dp),
                                    )
                                }else{
                                    Text(
                                        text = "Opponent",
                                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Black,
                                        softWrap = true,
                                        fontFamily= poppins,
                                        maxLines = 1,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 4.dp),
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .height(64.dp)
                                        .aspectRatio(1 / 1f)
                                        .clip(RoundedCornerShape(25))
                                        .background(Color(0xff5DA118)),
                                    contentAlignment = Alignment.Center
                                ){
                                    OutlinedTextField(
                                        value = score2,
                                        onValueChange = {score2 = it},
                                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                        textStyle = TextStyle(
                                            color = (if (isSystemInDarkTheme()) Color.Black else Color.White),
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                        ),
                                        singleLine = true,
                                        colors = TextFieldDefaults.outlinedTextFieldColors(disabledBorderColor = Color.Transparent, focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent),
                                    )
                                }

                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview(
    showBackground = false,
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false
)
@Preview(
    showBackground = false,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = false
)
@Composable
fun CardSetPreview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            CardSetView(
//                "set 1",
//                Set(0,0,"","",1,1),
//                Player(null,0,"Rafi"),
//                Player(null,0,"Raf2"),
//                viewModel(),
//                sets
//            )
        }
    }
}