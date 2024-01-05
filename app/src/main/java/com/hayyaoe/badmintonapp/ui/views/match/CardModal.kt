package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardModalWaitingView() {
    Card(onClick = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 2f)
                .background(
                    color = Color(0xff5DA118),
                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                )
                .padding(16.dp, 16.dp, 16.dp, 16.dp),
            contentAlignment = Alignment.TopStart
        ){
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Close",
                modifier = Modifier
                    .height(48.dp)
                    .aspectRatio(1f)
                    .rotate(45f)
            )
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){
                Column {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Loading",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(144.dp)
                    )
                    Text(text ="Waiting for other player...",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 16.dp, 16.dp, 16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardModalAcceptedView() {
    Card(onClick = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 2f)
                .background(
                    color = Color(0xff5DA118),
                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                )
                .padding(16.dp, 16.dp, 16.dp, 16.dp),
            contentAlignment = Alignment.TopStart
        ){
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Close",
                modifier = Modifier
                    .height(48.dp)
                    .aspectRatio(1f)
                    .rotate(45f)
            )
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){
                Column {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Loading",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(144.dp)
                    )
                    Text(text ="Score Accepted",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 16.dp, 16.dp, 16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardModalDeclinedView() {
    Card(onClick = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 2f)
                .background(
                    color = Color(0xff5DA118),
                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp, 24.dp)
                )
                .padding(16.dp, 16.dp, 16.dp, 16.dp),
            contentAlignment = Alignment.TopStart
        ){
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Close",
                modifier = Modifier
                    .height(48.dp)
                    .aspectRatio(1f)
                    .rotate(45f)
            )
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){
                Column {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Loading",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(144.dp)
                    )
                    Text(text ="Score Declined",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 16.dp, 16.dp, 16.dp)
                    )
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
fun CardModaWaitinglPreview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CardModalWaitingView()
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
fun CardModalAcceptedPreview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CardModalAcceptedView()
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
fun CardModalDeclinedPreview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CardModalDeclinedView()
        }
    }
}