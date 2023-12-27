package com.hayyaoe.badmintonapp.ui.views.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.R



@Composable
fun RegisterView(){

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("")}
    var password by rememberSaveable {mutableStateOf("")}
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    var level by rememberSaveable { mutableStateOf("") }

    val skillLevels: MutableList<String> = mutableListOf("Beginner", "Intermediate", "Advanced")

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color.White)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 180.dp)
                .shadow(60.dp, shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp), ambientColor = if (isSystemInDarkTheme()) Color(
                    0xFFFFFFFF
                ) else Color(0xFF000000)
                ),
            colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color.White),
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        ) {

            Column(
                modifier = Modifier.padding(top = 40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column {
                    Text(
                        text = "Register",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    )

                    Text(
                        text = "Create your account",
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        color = if (isSystemInDarkTheme()) Color(0xFFC6CEBE) else Color(0xFF5DA119),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    )
                }


                CustomTextBox(
                    name,
                    { name = it },
                    "Name"
                )
                CustomTextBox(
                    email,
                    { email = it },
                    "Email",
                    KeyboardType.Email
                )
                CustomTextBox(
                    password,
                    { password = it },
                    "Password",
                    KeyboardType.Password
                )
                CustomTextBox(
                    confirmPassword,
                    { confirmPassword = it },
                    "Confirm Password",
                    KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
                CustomDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it },
                    value = level,
                    onValueChange = { level = it },
                    isError = false,
                    options = skillLevels,
                    label = "Skill Level"
                )
                CustomButton({}, "REGISTER", Modifier.padding(vertical =20.dp, horizontal = 26.dp))
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
fun RegisterViewPreview(){
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        RegisterView()
    }
}
