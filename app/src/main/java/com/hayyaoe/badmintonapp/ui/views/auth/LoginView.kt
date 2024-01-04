package com.hayyaoe.badmintonapp.ui.views.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.viewmodel.auth.LoginViewModel

@Composable
fun LoginView(
    loginViewModel: LoginViewModel,
    navController: NavController,
    dataStore: DataStoreManager
){

    val loginUiState by loginViewModel.uiState.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("")}

    val context = LocalContext.current

    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color.White)
    ) {
        item {
            Box {
                Image(painter = painterResource(id = R.drawable.preview), contentDescription = "Preview")
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
                                color = if (isSystemInDarkTheme()) Color(0xFFC6CEBE) else Color(
                                    0xFF5DA119
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 50.dp)
                            )
                        }

                        CustomTextBox(
                            value = email,
                            onValueChange = { email = it },
                            label = "Email",
                            errorMessage = "Invalid Email"
                        )
                        CustomTextBox(
                            value = password,
                            onValueChange = { password = it },
                            isPassword = true,
                            label = "Password",
                            errorMessage = "Incorrect Password"
                        )
                        CustomButton(
                            onClick = { loginViewModel.login(password= password, email= email, dataStore = dataStore, navController = navController) },
                            content = "LOGIN",
                            Modifier.padding(top = 250.dp, start = 26.dp, end = 26.dp, bottom =50.dp),
                            email.isNotBlank() && password.isNotBlank()
                        )

                    }
                }
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
        LoginView(viewModel(),navController(), DataStoreManager(LocalContext.current))
    }
}