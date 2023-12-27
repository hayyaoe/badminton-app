package com.hayyaoe.badmintonapp.ui.views.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginView(){

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("")}

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color.White)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 180.dp)
                .shadow(
                    60.dp,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                    ambientColor = if (isSystemInDarkTheme()) Color(
                        0xFFFFFFFF
                    ) else Color(0xFF000000)
                ),
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) Color(
                    0xFF191C1E
                ) else Color.White
            ),
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        ) {

            Column(
                modifier = Modifier.padding(top = 40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column {
                    Text(
                        text = "Login",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    )

                    Text(
                        text = "Sign in to your account",
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        color = if (isSystemInDarkTheme()) Color(0xFFC6CEBE) else Color(0xFF5DA119),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    )
                }

                    CustomTextBox(value = email, onValueChange = { email = it }, label = "Email")
                    CustomTextBox(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password"
                    )
                    CustomButton(onClick = { }, content = "LOGIN", Modifier.padding(top = 250.dp,start = 26.dp, end =26.dp))

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
fun LoginViewPreview(){
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        LoginView()
    }
}