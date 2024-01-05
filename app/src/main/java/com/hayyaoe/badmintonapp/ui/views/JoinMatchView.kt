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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.ui.views.auth.CustomTextBox
import com.hayyaoe.badmintonapp.ui.views.auth.poppins
import com.hayyaoe.badmintonapp.viewmodel.home.JoinMatchViewModel

@Composable
fun JoinMatchView(
    joinMatchViewModel: JoinMatchViewModel,
    navController: NavController
) {
    var matchCode by rememberSaveable { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showLoadingDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Join Match",
            fontSize = 44.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 50.dp),
            fontFamily = poppins
        )
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = "Picture",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(50.dp, 16.dp)
                .fillMaxWidth()
        )
        CustomTextBox(
            value = matchCode,
            onValueChange = {matchCode= it},
            label = "Enter Match Code",
            errorMessage = "Not Found",
        )
        CustomButton(
            onClick = {
                isLoading = true
                showLoadingDialog = true
            },
            content = "JOIN MATCH",
            isEnabled = matchCode.isBlank(),
            modifier = Modifier.padding(horizontal = 26.dp, vertical = 20.dp)
        )
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
    JoinMatchView(viewModel(), navController())
}
