package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.ui.views.auth.poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchProcessView(){

    val navController = navController()
    val context = LocalContext

    val showScore by remember{ mutableStateOf(false) }
    val scores by remember{ mutableStateOf("")}

@Composable
fun ScorePopup(){

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
                            PlayerCard()
                        }
                        item {
                            CustomButton(onClick = { /*TODO*/ }, content = "Finish Match", modifier = Modifier.padding(top = 30.dp))
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
private fun matchProcessPreview(){
MatchProcessView()
}