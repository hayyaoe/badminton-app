package com.hayyaoe.badmintonapp.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JoinMatchView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF1F1F1))
            .padding (32.dp, 40.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Join Match",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JoinMatchPreview(){
    JoinMatchView()
}
