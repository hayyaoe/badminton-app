package com.hayyaoe.badmintonapp.ui.view

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.R

@Composable
fun HistoryView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp, 0.dp, 32.dp, 0.dp)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = "History",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 12.dp)
                )
                HistoryCard(
                    date = "19 December 2024",
                    player = "Bob Hee",
                    opponent = "Pak Evan",
                    details = "Bob Hee played really well",
                    yourScore = 2,
                    opponentScore = 1
                )

                HistoryCard(
                    date = "20 December 2024",
                    player = "Bob Mark",
                    opponent = "Pak Evan",
                    details = "Bob played really bad",
                    yourScore = 1,
                    opponentScore = 2
                )

                HistoryCard(
                    date = "21 December 2024",
                    player = "Bob Mark",
                    opponent = "Pak Evan",
                    details = "Bob played really bad",
                    yourScore = 2,
                    opponentScore = 2
                )
            }
        }
    }
}

@Composable
fun HistoryCard(
    date: String,
    player: String,
    opponent: String,
    details: String,
    yourScore: Int,
    opponentScore: Int
) {
    val backgroundColor = if (yourScore > opponentScore) {
        Color(0xFF5DA119)
    } else if (opponentScore > yourScore) {
        Color(0xFFD31A1A)
    } else {
        Color(0xFFE7B811)
    }

    val secondBackgroundColor = if (yourScore > opponentScore) {
        Color(0xFF53E6E0E)
    } else if (opponentScore > yourScore) {
        Color(0xFF9F1515)
    } else {
        Color(0xFFB99412)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 44.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = date,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(0.dp, 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 32.dp, 32.dp, 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Your Picture",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                            .clip(RoundedCornerShape(32.dp))
                    )
                    Text(
                        text = player,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(0.dp, 8.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(0.dp, 20.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "VS",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = backgroundColor
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Opponent Picture",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp)
                            .clip(RoundedCornerShape(32.dp))
                    )
                    Text(
                        text = opponent,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(0.dp, 8.dp)
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = secondBackgroundColor
                )
                Text(
                    text = details,
                    fontSize = 16.sp
                )
            }
        }
        Card (
            modifier = Modifier
                .width(150.dp)
                .align(Alignment.BottomCenter)
                .offset(0.dp, 36.dp),
            colors = CardDefaults.cardColors(backgroundColor),
            elevation = CardDefaults.cardElevation(10.dp)
        ){
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(
                            color = if (yourScore < opponentScore) {
                                backgroundColor
                            } else { secondBackgroundColor },
                            shape = if (yourScore < opponentScore) {
                                RoundedCornerShape(0.dp)
                            } else { RoundedCornerShape(12.dp) }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = yourScore.toString(),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(
                            color = if (yourScore < opponentScore) {
                                secondBackgroundColor
                            } else { backgroundColor },
                            shape = if (yourScore < opponentScore) {
                                RoundedCornerShape(12.dp)
                            } else { RoundedCornerShape(0.dp) }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = opponentScore.toString(),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HistoryPreview() {
    HistoryView()
}