package com.hayyaoe.badmintonapp.ui.views.auth

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomFileSelectionBox(
    value: String,
    onValueChange: (Uri?) -> Unit,
    isError: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    label: String,
    onClick: ()-> Unit
) {

    Column (
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .background(if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color.Transparent)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppins,
            modifier = modifier.padding(start = 12.dp),
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
        Box(
            modifier = modifier
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(14.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isSystemInDarkTheme()) Color(0xFF4C4E49) else if (isError) Color.Red else Color(
                        0xFFC6CEBE
                    ),
                    shape = RoundedCornerShape(14.dp)
                )
                .clip(RoundedCornerShape(14.dp))
                .clickable {}
                .height(55.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp)
                    .height(55.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = value.ifEmpty { "Select Photo" },
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                            fontFamily = poppins,
                        ),
                    )

                    Button(
                        onClick = onClick,
                        modifier = Modifier.height(34.dp),
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isSystemInDarkTheme()) Color(
                            0xFF313131
                        ) else Color(0xFFE9E6E6 )),
                        border = BorderStroke(1.dp, if (isSystemInDarkTheme()) Color(0xFF4C4E49) else if (isError) Color.Red else Color(0xFFC6CEBE) )
                    ) {
                        Text(text = "Select", fontFamily = poppins, fontSize = 12.sp,color = if (isSystemInDarkTheme()) Color.White else Color.Black)
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
fun CustomFileSelectionPreview(){
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
         var selectedImage by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

        CustomFileSelectionBox(

            value = "",
            onValueChange = {selectedImage = it},
            isError = false ,
            label = "Profile Picture",
            onClick = {}
        )
    }
}