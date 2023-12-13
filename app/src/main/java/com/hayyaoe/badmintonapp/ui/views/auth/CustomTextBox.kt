package com.hayyaoe.badmintonapp.ui.views.auth

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.find.CardFindView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 50.dp, 0.dp),
    imeAction: ImeAction = ImeAction.Next,
    isError: Boolean = false

) {

    Column{
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppins,
            modifier = modifier.padding(start = 12.dp),
            color = if (isSystemInDarkTheme()) Color.White else Color.Black

        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.height(50.dp),
            shape = RoundedCornerShape(14.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.None,
                false,
                keyboardType,
                imeAction
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color(0xFF819171),
                unfocusedBorderColor = if (isSystemInDarkTheme()) Color(0xFF4C4E49) else Color(0xFFC6CEBE),
                textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            ),
            isError = isError,
            textStyle = TextStyle(fontSize = 12.sp, fontFamily = poppins, color = Color.Black)

        )
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
fun CustomTextBoxPreview(){
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            var test by rememberSaveable { mutableStateOf("") }
           CustomTextBox(value = test, onValueChange = {test= it}, label = "Test")
        }
    }
}
