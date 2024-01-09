package com.hayyaoe.badmintonapp.ui.views.match

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.model.Player
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.auth.poppins


@SuppressLint("ResourceType")
@Composable
fun PlayerCard (player: Player, context: Context) {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White)
    ){
        Column (
        ){
            AsyncImage(
                model = ImageRequest.Builder(context = context).data(BadmintonContainer.API_URL+player.photo).crossfade(true).build(),
                contentDescription ="Spartner Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1.1f)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
                )

            Row (
                Modifier.padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 5.dp)
            ){
                Column (
                    Modifier.weight(2f)
                ){
                    Text(
                        text = "You're sparing with",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF5DA119)
                    )
                    Text(
                        text = player.username,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.offset(y = (-8).dp)
                    )
                }
//                Column (
//                    Modifier.weight(1f),
//                    horizontalAlignment = Alignment.End,
//                ){
////                    Row(
////                        modifier = Modifier
////                            .padding(top = 20.dp)
////                            .background(Color(0xFF5DA119), RoundedCornerShape(5.dp)),
////                        verticalAlignment = Alignment.CenterVertically
////                        ){
////                        Text(
////                            text = "400",
////                            fontFamily = poppins,
////                            fontSize = 12.sp,
////                            color = Color.White,
////                            modifier= Modifier.padding(top =2.dp, bottom = 2.dp, start = 6.dp).offset(y=1.dp)
////                        )
////                        Text(
////                            text = "\uD83D\uDD25",
////                            fontSize = 10.sp,
////                            modifier= Modifier.padding( end = 6.dp).offset(y= (-1).dp)
////                        )
////                    }
//
//                }
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
private fun PlayerCardPreview(){
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {

            PlayerCard(Player("",1,"Pak Evan"), LocalContext.current)
        }
    }
}