package com.hayyaoe.badmintonapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.R

@Composable
fun HomeView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
            ) {
                Text(
                    text = "Hi, John!",
                    color = Color(0xFF5DA119),
                    fontSize = 18.sp,
                    modifier = Modifier
                )
                Text(
                    text = "Find Your RacketMate!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(
                    onClick = {

                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(0.dp)
                        .align(Alignment.End),
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFF5DA119),
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notification Icon",
                        modifier = Modifier
                            .padding(0.dp)
                            .align(Alignment.End),
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                MenuCard(image = R.drawable.placeholder, "Find Spartner")
                MenuCard(image = R.drawable.placeholder, "Create Match")
                MenuCard(image = R.drawable.placeholder, "Join Match")
                MenuCard(image = R.drawable.placeholder, "History")
            }
        }
    }
}

@Composable
fun MenuCard(
    image: Int,
    text: String
) {
    Button(
        onClick = {
            /*TODO*/
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(4.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(38.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(38.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Picture",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                        .graphicsLayer(
                            translationY = 0.3f // Adjust this value to control the gradient start position
                        )
                )
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 24.dp, bottom = 12.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    HomeView()
}