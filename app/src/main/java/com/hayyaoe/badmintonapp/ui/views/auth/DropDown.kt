package com.hayyaoe.badmintonapp.ui.views.auth

import android.content.res.Configuration
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
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier,
    label: String,
    options : MutableList<String>
) {

    Column (
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .background(if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color.Transparent)
    ){
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
                .clickable { onExpandedChange(!expanded) }
                .height(50.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = value.ifEmpty { "Select" },
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                            fontFamily = poppins,
                        ),
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    )
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier
                    .background(
                        color = if (isSystemInDarkTheme()) Color(0xFF191C1E) else Color.White,
                    )
            ) {

                for (option in options){
                    DropdownMenuItem(
                        text = { Text(text = option, color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontFamily = poppins) },
                        onClick = {
                            onValueChange(option)
                            onExpandedChange(false)
                        })
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
fun DropDownPreview(){
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        var level by rememberSaveable {
            mutableStateOf("")
        }

        var isExpanded by remember{
            mutableStateOf(false)
        }

        val skillLevels: MutableList<String> = mutableListOf("Beginner", "Intermediate", "Advanced")


        CustomDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = it},
            value = level,
            onValueChange = {level = it},
            isError = false ,
            options = skillLevels,
            label = "Skill Level"
        )
    }
}