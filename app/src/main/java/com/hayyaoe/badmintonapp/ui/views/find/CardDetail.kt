package com.hayyaoe.badmintonapp.ui.views.find

import android.R
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.getResId
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailView(
    _profilePicture: String,
    _name: String,
    _location: String,
    _phone: String,
    _instagram: String,
    onCardClick: () -> Unit
) {

    val profilePicture: Int = getResId(_profilePicture)
    val name: String = _name
    val location: String = _location
    val phone: String = _phone
    val instagram: String = _instagram

    Card(
        onClick = {},
        modifier = Modifier
            .padding(12.dp)
            .border(
                1.dp,
                if (isSystemInDarkTheme()) Color.Black else Color.White,
                RoundedCornerShape(12.dp)
            ),
//            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = profilePicture),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3 / 2f)
                        .clip(RoundedCornerShape(9.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
            }
            Text(
                text = name,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                softWrap = true,
                maxLines = 2,
                modifier = Modifier
                    .padding(end = 60.dp),
            )
            Text(
                text = location,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                softWrap = true,
                maxLines = 2,
                lineHeight = 15.sp,
                modifier = Modifier
                    .padding(end = 60.dp, bottom = 12.dp),
            )
            Text(
                text = phone,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                softWrap = true,
                maxLines = 2,
                modifier = Modifier
                    .padding(end = 60.dp),
            )
            Text(
                text = buildAnnotatedString {
                    append("IG @")
                    append(instagram)
                },
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                softWrap = true,
                maxLines = 2,
                lineHeight = 15.sp,
                modifier = Modifier
                    .padding(end = 60.dp, bottom = 24.dp),
            )
            Button(
                onClick = onCardClick,
                shape = RoundedCornerShape(9.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff5DA118)
                )
            ) {
                Text(
                    text = "ADD FRIEND",
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false
)
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = false
)
@Composable
fun CardDetailPreview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CardDetailView(
                "rafi",
                "Rafi Abhista Naya",
                "WP, Citraland UC WP, Citraland UC WP, Citraland UC",
                "091234567890",
                "abhista_naya",
                onCardClick = {}
            )
        }
    }
}