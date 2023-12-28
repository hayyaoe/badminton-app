package com.hayyaoe.badmintonapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.R

@Composable
fun JoinMatchView() {
    var matchCode by rememberSaveable { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showLoadingDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding (32.dp, 40.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Join Match",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = "Picture",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(0.dp, 16.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Enter Match Code",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 18.dp)
        )
        CustomTextField(
            value = matchCode,
            onValueChanged = { matchCode = it },
            text = "Match Code",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 4.dp, 0.dp, 8.dp)
        )
        Button(
            onClick = {
                showLoadingDialog = true
                isLoading = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF5DA119)),
            shape = RoundedCornerShape(14.dp),
            enabled = matchCode.isNotBlank()
        ) {
            Text(text = "CONFIRM MATCH", fontWeight = FontWeight.SemiBold)
        }
    }
    if (showLoadingDialog) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
        ) {
            Text(text = "")
        }
        LoadingDialog()
    }
}

@Composable
fun LoadingDialog() {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = { },
        title = { Text(text = "Joining") },
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        },
        containerColor = Color(0xFF5DA119),
        titleContentColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JoinMatchPreview(){
    JoinMatchView()
}
