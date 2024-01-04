package com.hayyaoe.badmintonapp.ui.views.match

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.auth.CustomTextBox
import com.hayyaoe.badmintonapp.ui.views.auth.poppins

@Composable
fun CommentCard(
    onValueChange: (String) -> Unit,
    image: Int
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
            .clip(shape = RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White)
    ){
        Column (
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription ="Spartner Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.8f / 1.1f)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )

            Row (
                Modifier.padding(start = 30.dp, end = 30.dp, top = 20.dp, bottom = 5.dp)
            ){
                Column (
                    Modifier.weight(2f)
                ){
                    Text(
                        text = "You're sparing with",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF5DA119)
                    )
                    Text(
                        text = "Pak Evan",
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.offset(y = (-8).dp)
                    )
                }
                Column (
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                ){
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .background(Color(0xFF5DA119), RoundedCornerShape(5.dp)),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "400",
                            fontFamily = poppins,
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier= Modifier
                                .padding(top = 2.dp, bottom = 2.dp, start = 6.dp)
                                .offset(y = 1.dp)
                        )
                        Text(
                            text = "\uD83D\uDD25",
                            fontSize = 10.sp,
                            modifier= Modifier
                                .padding(end = 6.dp)
                                .offset(y = (-1).dp)
                        )
                    }
                }
            }
            LargeTextBox(value = "Comment", onValueChange = onValueChange, label = "Comment their play!" )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 28.dp, 0.dp),
    imeAction: ImeAction = ImeAction.Next,
    isError: Boolean = false

) {

    Column(
        Modifier.padding(bottom = 28.dp)
    ){
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppins,
            modifier = modifier,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black

        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.height(160.dp),
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
                containerColor = if (isSystemInDarkTheme()) Color(0xFF0A0A0A) else Color(0xFFF8F8F8),
            ),
            isError = isError,
            textStyle = TextStyle(fontSize = 12.sp, fontFamily = poppins, color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray, lineBreak = LineBreak.Paragraph)

        )
    }

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
private fun CommentPreview(){

    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {

            var comment by rememberSaveable { mutableStateOf("") }
            CommentCard(image = R.drawable.evan_tanuwijaya__s_kom___m_kom_resize, onValueChange = {comment = it} )
        }
    }
}
