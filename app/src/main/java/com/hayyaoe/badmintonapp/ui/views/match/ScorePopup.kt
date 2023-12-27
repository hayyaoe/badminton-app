package com.hayyaoe.badmintonapp.ui.views.match

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme

@Composable
fun ScorePopUp(){

}

@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    showSystemUi = false,
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ScorePopUpPreview(){

    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ScorePopUp()
        }
    }
}