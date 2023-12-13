package com.hayyaoe.badmintonapp.ui.views.auth

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme


@Composable
fun CustomButton(
    onClick: () -> Unit,
    content: String,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color(0xFF5DA119), Color(0xFFF9F9F9)),
        modifier = modifier.height(50.dp).fillMaxWidth().padding(horizontal = 50.dp),
        shape = RoundedCornerShape(14.dp),
    ) {
        Text(
            text = content,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}


val poppins = FontFamily(
    Font(R.font.poppinsbold, FontWeight.Bold),
    Font(R.font.poppinsmedium, FontWeight.Medium),
    Font(R.font.poppinsregular, FontWeight.Normal),
    Font(R.font.poppinssemibold, FontWeight.SemiBold)
)

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
fun CustomButtonPreview(){
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            var test by rememberSaveable { mutableStateOf("") }
            CustomButton(onClick = { /*TODO*/ }, content = "LOGIN")
        }
    }
}

