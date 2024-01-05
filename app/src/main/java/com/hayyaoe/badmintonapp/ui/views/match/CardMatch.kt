package com.hayyaoe.badmintonapp.ui.views.match

import android.R
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.getResId
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMatchView(
    _profilePicture1: String,
    _name1: String,
    _profilePicture2: String,
    _name2: String,
    _score1: String,
    _score2: String,
    _date: String,
    _detail: String,
    onCardClick: () -> Unit
) {

    val profilePicture1: Int = getResId(_profilePicture1)
    val profilePicture2: Int = getResId(_profilePicture2)
    val name1: String = _name1
    val name2: String = _name2
    val score1: String = _score1
    val score2: String = _score2
    val date: String = _date
    val detail: String = _detail

    Card(
        onClick = {},
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 60.dp)
            .border(
                1.dp,
                if (isSystemInDarkTheme()) Color.Transparent else Color.Transparent,
                RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
            )
            .background(Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
                    .background(
                        Color(0xff5DA118),
                        shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date,
                    color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    softWrap = true,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
                    .background(
                        Color(if (isSystemInDarkTheme()) 0xff1E1E1E else 0xffF2F2F2),
                        shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp, 12.dp, 12.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .width(128.dp)
                                .aspectRatio(2 / 3f)
                                .weight(6f),
                        ) {
                            Image(
                                painter = painterResource(id = profilePicture1),
                                contentDescription = "profile picture",
                                modifier = Modifier
                                    .height(128.dp)
                                    .aspectRatio(1 / 1f)
                                    .clip(RoundedCornerShape(9.dp)),
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.TopCenter
                            )
                            Text(
                                text = name1,
                                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                softWrap = true,
                                maxLines = 2,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )
                        }

                        Text(
                            text = "VS",
                            color = Color(0xff5DA118),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black,
                            softWrap = true,
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                        )

                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
                                .width(128.dp)
                                .aspectRatio(2 / 3f)
                                .weight(6f),
                        ) {
                            Image(
                                painter = painterResource(id = profilePicture2),
                                contentDescription = "profile picture",
                                modifier = Modifier
                                    .height(128.dp)
                                    .aspectRatio(1 / 1f)
                                    .clip(RoundedCornerShape(9.dp)),
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.TopCenter
                            )
                            Text(
                                text = name2,
                                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                softWrap = true,
                                maxLines = 2,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )
                        }
                    }

                    Text(
                        text = "Details",
                        color = Color(0xff5DA118),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        softWrap = true,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )

                    Text(
                        text = detail,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        softWrap = true,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(12.dp, 0.dp, 12.dp, 12.dp)
                            .fillMaxWidth(),
                    )

                    Box(
                        modifier = Modifier
                            .height(96.dp)
                    )

                }
            }
        }
    }
}


@Composable
fun CardMatchViewExtend(
    _profilePicture1: String,
    _name1: String,
    _profilePicture2: String,
    _name2: String,
    _score1: String,
    _score2: String,
    _date: String,
    _detail: String,
    onCardClick: () -> Unit
){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 12.dp, 12.dp, 12.dp)
            .border(
                1.dp,
                if (isSystemInDarkTheme()) Color.Transparent else Color.Transparent,
//                RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
            ),
        contentAlignment = Alignment.BottomCenter
    ){
        CardMatchView(
            _profilePicture1,
            _name1,
            _profilePicture2,
            _name2,
            _score1,
            _score2,
            _date,
            _detail
        ) {
            onCardClick()
        }
        Box(
            modifier = Modifier
                .width(256.dp)
                .height(128.dp)
                .background(Color(0XFF5DA118), RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp))
                .padding(0.dp)
                .border(
                    1.dp,
                    if (isSystemInDarkTheme()) Color.Transparent else Color.Transparent,
                    RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){


                Box(
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp)
                        .background(
                            Color(0xFF3D6910),
                            RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                        )
                        .padding(0.dp)
                        .border(
                            1.dp,
                            if (isSystemInDarkTheme()) Color.Transparent else Color.Transparent,
                            RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = _score1,
                        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.SemiBold,
                        softWrap = true,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp, 12.dp, 12.dp, 12.dp),
                    )
                }
                Text(
                    text = _score2,
                    color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold,
                    softWrap = true,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 12.dp, 12.dp, 12.dp),
                )
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
fun CardMatchPreview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            CardMatchView(
//                "rafi",
//                "name1",
//                "rafi",
//                "name2",
//                "score1",
//                "score2",
//                "12 Desember 2021",
//                "detail detail detail detail detail detail detail detail detail detail detail detail detail detail detail detail ",
//                onCardClick = {}
//            )
            CardMatchViewExtend(
                "rafi",
                "name1",
                "rafi",
                "name2",
                "300",
                "200",
                "12 Desember 2021",
                "detail detail detail detail detail detail detail detail detail detail detail detail detail detail detail detail ",
                onCardClick = {}
            )
        }
    }
}