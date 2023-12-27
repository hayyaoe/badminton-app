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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserDetailsView (){

    var profile by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var region by rememberSaveable { mutableStateOf("") }
    var isExpanded1 by remember { mutableStateOf(false) }
    var isExpanded2 by remember { mutableStateOf(false) }

    val regions: MutableList<String> = mutableListOf("Lakarsantri", "Made", "Tandes")
    val cities: MutableList<String> = mutableListOf("Surabaya", "Surabaya Lagi", "Surabaya")

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
                .shadow(
                    60.dp,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                    ambientColor = if (isSystemInDarkTheme()) Color(
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
                        text = "Address and Contact",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    )

                    Text(
                        text = "Complete your details",
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        color = if (isSystemInDarkTheme()) Color(0xFFC6CEBE) else Color(0xFF5DA119),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                    )
                }

                CustomFileSelectionBox(
                    value =profile ,
                    onValueChange = {profile = it},
                    label = "Profile Picture")

                CustomDropdownMenuBox(
                    expanded = isExpanded1,
                    onExpandedChange = { isExpanded1 = it },
                    value = city,
                    onValueChange = { city = it },
                    isError = false,
                    options = cities,
                    label = "City"
                )

                CustomDropdownMenuBox(
                    expanded = isExpanded2,
                    onExpandedChange = { isExpanded2 = it },
                    value = region,
                    onValueChange = { region = it },
                    isError = false,
                    options = regions,
                    label = "Region"
                )

                CustomTextBox(
                    phone,
                    { phone = it },
                    "Password",
                    KeyboardType.Password
                )

                CustomButton({}, "REGISTER", Modifier.padding(vertical =60.dp, horizontal = 26.dp))
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
fun UserDetailsViewPreview(){
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        UserDetailsView()
    }
}