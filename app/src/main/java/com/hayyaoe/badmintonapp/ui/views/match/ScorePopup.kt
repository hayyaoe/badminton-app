package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.ui.views.auth.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScorePopUp(
    score1 : Int = 0,
    score2 : Int = 0,
    isWon1 : Boolean  = false,
    isWon2 : Boolean  = false,
    player1_name : String = "Bob Hee",
    player2_name : String = "Pak Evan",
    onClick : ()-> Unit
){

    Card (
        onClick = onClick,
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
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(Icons.Filled.Close, "Close Icon",
                        modifier = Modifier
                            .background(Color(0xFF5DA119), CircleShape)
                            .padding(6.dp),
                        tint = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
                    )
                }

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
                Text(text = "       ")
                
            }

            Box (
                modifier = Modifier.padding(top = 30.dp),
                contentAlignment = Alignment.Center
            ){
                Row (
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ){

                    UserProfile(drawable = R.drawable.rafi, player1_name)
                    UserProfile(drawable = R.drawable.evan_tanuwijaya__s_kom___m_kom_resize, player2_name)
                }

                ScoreBoard(score1, score2, isWon1, isWon2, Modifier.offset(y= (-20).dp))

            }

            Set(setNumber= 1)
            Set(setNumber= 2)
            Set(setNumber= 3)

            Buttons(onClick = { /*TODO*/ }, content = "Accept", colors = ButtonDefaults.buttonColors(Color(0xFF5DA119), Color(0xFFF9F9F9)), modifier = Modifier.padding(top = 20.dp,bottom= 10.dp))
            Buttons(onClick = { /*TODO*/ }, content = "Decline", colors = ButtonDefaults.buttonColors(Color(0xFFCE392E), Color(0xFFF9F9F9)) )


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
    drawable: Int,
    player_name : String
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
            Image(
                painter = painterResource(id = drawable), contentDescription = "Player 1",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(135.dp)
                    .clip(shape = RoundedCornerShape(16))
            )
        }
        Text(
            text = player_name,
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}

@Composable
fun ScoreBoard(
    score1 : Int = 0,
    score2 : Int = 0,
    isWon1 : Boolean  = false,
    isWon2 : Boolean  = false,
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
            ScorePopUp(2,1,true,false, onClick = {})
        }
    }
}