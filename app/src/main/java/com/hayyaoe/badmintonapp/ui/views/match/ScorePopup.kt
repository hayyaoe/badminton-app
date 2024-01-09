package com.hayyaoe.badmintonapp.ui.views.match

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.RoundedCorner
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.model.CreateGameResponse
import com.hayyaoe.badmintonapp.model.Game
import com.hayyaoe.badmintonapp.model.GetGameData
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.ui.views.auth.poppins
import com.hayyaoe.badmintonapp.viewmodel.home.MatchProcessViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScorePopUp(
    onClick1 : ()-> Unit,
    onClick2 : ()-> Unit,
    game: GetGameData,
    matchProcessViewModel: MatchProcessViewModel,
    context: Context = LocalContext.current
){

    Card (
        onClick = {},
        colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Match Score",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .offset(y = 1.dp)
                        .weight(2f),
                    textAlign = TextAlign.Center
                )

            }

            Box (
                modifier = Modifier.padding(top = 30.dp),
                contentAlignment = Alignment.Center
            ){
                Row (
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ){

                    if (game.players!= null){
                        game.players[0].photo?.let { UserProfile(drawable = it, game.players[0].username, context, Modifier.size(110.dp), fontSize = 20.sp) }
                        game.players[1].photo?.let { UserProfile(drawable = it, game.players[1].username, context, Modifier.size(110.dp), fontSize = 20.sp) }
                    }

                }

                ScoreBoard(game.score_1, game.score_2, matchProcessViewModel.isWon(game.score_1, game.score_2), matchProcessViewModel.isWon(game.score_2, game.score_1), Modifier.offset(y= (-20).dp))

            }
            Log.d("SET DATA", game.sets.toString())
            Set(
                setNumber= 1,
                score1 = game.sets.get(0).player1_score,
                score2 = game.sets.get(0).player2_score,
                isWon1 = matchProcessViewModel.isWon(game.sets.get(0).player1_score,game.sets.get(0).player2_score),
                isWon2 = matchProcessViewModel.isWon(game.sets.get(0).player2_score,game.sets.get(0).player1_score )
            )
            Set(
                setNumber= 2,
                score1 = game.sets.get(1).player1_score,
                score2 = game.sets.get(1).player2_score,
                isWon1 = matchProcessViewModel.isWon(game.sets.get(1).player1_score,game.sets.get(1).player2_score),
                isWon2 = matchProcessViewModel.isWon(game.sets.get(1).player2_score,game.sets.get(1).player1_score ))
            if (game.sets.size==3){
                Set(
                    setNumber= 3,
                    score1 = game.sets.get(2).player1_score,
                    score2 = game.sets.get(2).player2_score,
                    isWon1 = matchProcessViewModel.isWon(game.sets.get(2).player1_score,game.sets.get(2).player2_score),
                    isWon2 = matchProcessViewModel.isWon(game.sets.get(2).player2_score,game.sets.get(2).player1_score ))
            }


                    Buttons(onClick = onClick1, content = "Accept", colors = ButtonDefaults.buttonColors(Color(0xFF5DA119), Color(0xFFF9F9F9)), modifier = Modifier.padding(top = 20.dp,bottom= 10.dp))
            Buttons(onClick = onClick2, content = "Decline", colors = ButtonDefaults.buttonColors(Color(0xFFCE392E), Color(0xFFF9F9F9)) )


        }
    }
}

@Composable
private fun Buttons(
    onClick: ()->Unit,
    content: String,
    colors: ButtonColors,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
        shape = RoundedCornerShape(14.dp),
    ) {
        Text(
            text = content,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun Set(
    modifier: Modifier = Modifier,
    setNumber: Int = 1,
    score1 : Int = 0,
    score2 : Int = 0,
    isWon1 : Boolean  = false,
    isWon2 : Boolean  = false,
){
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ){
        Column (
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(20))){
            Text(
                text = "Set $setNumber",
                fontSize = 18.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
            )
        }
        ScoreBoard(score1, score2, isWon1, isWon2)
    }
}

@Composable
fun UserProfile(
    drawable: String,
    player_name : String,
    context: Context,
    modifier: Modifier= Modifier,
    fontSize: TextUnit = 24.sp
){
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Box(
            modifier = Modifier
                .border(
                    shape = RoundedCornerShape(16),
                    width = 1.dp,
                    color = if (isSystemInDarkTheme()) Color(0xFF6B6B6B) else Color(
                        0xFFECECEC
                    )
                )
                .padding(6.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = context).data(BadmintonContainer.API_URL+drawable).crossfade(true).build()
                , contentDescription = "Player 1",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(135.dp)
                    .clip(shape = RoundedCornerShape(16))
            )
        }
        Text(
            text = player_name,
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ScoreBoard(
    score1 : Int = 0,
    score2 : Int = 0,
    isWon1: Boolean,
    isWon2: Boolean,
    modifier: Modifier = Modifier
){



    Card (
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = modifier
    ){
        Row (
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20))
                .background(color = Color(0xFF5DA119))

        ){
            Box(modifier = if(isWon1) Modifier.background(Color(0xFF467913), RoundedCornerShape(20)) else Modifier){
                Text(
                    text = score1.toString(),
                    fontSize = 24.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 20.dp)
                        .offset(y = 2.dp)


                )
            }

            Box(modifier = if(isWon2) Modifier.background(Color(0xFF467913), RoundedCornerShape(20)) else Modifier){
                Text(
                    text = score2.toString(),
                    fontSize = 24.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 20.dp)
                        .offset(y = 2.dp)

                )
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
private fun ScorePopUpPreview(){

    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            ScorePopUp( onClick1 = {}, onClick2={})
        }
    }
}