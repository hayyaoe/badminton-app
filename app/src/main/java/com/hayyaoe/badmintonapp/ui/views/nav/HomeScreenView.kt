package com.hayyaoe.badmintonapp.ui.views.nav

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.find.FindSpartnerView

//@Composable
//fun HomeScreen(modifier: Modifier = Modifier) {
//
//    val mContext = LocalContext.current
//
//    Column (
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(12.dp)
//    ){
//        Text(
//            text = "Home Screen",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Normal,
//            modifier = modifier
//                .padding(bottom = 24.dp)
//        )
//        Button(
//            onClick = {
//                mContext.startActivity(Intent(mContext, FindSpartner::class.java))
//            }
//        ) {
//            Text(text = "Find Spartner")
//        }
//    }
//}
//
//@Preview(
//    showBackground = true,
//    name = "Light Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    showSystemUi = true
//)
//@Preview(
//    showBackground = true,
//    name = "Dark Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showSystemUi = true
//)
//@Composable
//fun HomeScreenPreview() {
////    HomeScreen()
//    BadmintonAppTheme {
//        Surface(
//            color = MaterialTheme.colorScheme.background
//        ) {
//            HomeScreen()
//        }
//    }
//}