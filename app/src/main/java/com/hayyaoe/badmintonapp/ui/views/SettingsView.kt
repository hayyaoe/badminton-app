package com.hayyaoe.badmintonapp.ui.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hayyaoe.badmintonapp.R

@Composable
fun SettingsView() {
    var name by rememberSaveable { mutableStateOf("") }
    var profilePicture by rememberSaveable { mutableStateOf(0) }
    var city by rememberSaveable { mutableStateOf("") }
    var region by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var otherContact by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var selectedImage by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        selectedImage = it
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF1F1F1))
            .padding (0.dp, 32.dp, 0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(shape = CircleShape)
        )
        Row (
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                text = "John Doe",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit Icon"
            )
        }

        Card (
            elevation = CardDefaults.cardElevation(24.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(44.dp, 44.dp, 0.dp, 0.dp))
                .fillMaxHeight()

        ) {
            Column (
                modifier = Modifier
                    .padding(32.dp, 16.dp)
            ) {
                Text(
                    text = "Edit Your Profile",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp, 28.dp, 20.dp, 2.dp)
                )
                Text(
                    text = "New Profile Picture",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(32.dp, 18.dp, 20.dp, 0.dp)
                )
                Button(
                    onClick = {
                        galleryLauncher.launch("image/*")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF5DA119)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0xFF5DA119))
                ) {
                    Text(
                        text = "Select",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Right
                    )
                }
                Text(
                    text = "City",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(32.dp, 18.dp, 20.dp, 0.dp)
                )
                CustomTextField(
                    value = city,
                    onValueChanged = { city = it },
                    text = "City",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 2.dp)
                )
                Text(
                    text = "Region",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(32.dp, 4.dp, 20.dp, 0.dp)
                )
                CustomTextField(
                    value = region,
                    onValueChanged = { region = it },
                    text = "Region",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 2.dp)
                )
                Text(
                    text = "Phone",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(32.dp, 4.dp, 20.dp, 0.dp)
                )
                CustomTextField(
                    value = phone,
                    onValueChanged = { phone = it },
                    text = "Phone",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 2.dp)
                )
                Text(
                    text = "Other Contact",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(32.dp, 4.dp, 20.dp, 0.dp)
                )
                CustomTextField(
                    value = otherContact,
                    onValueChanged = { otherContact = it },
                    text = "Other Contact",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 2.dp)
                )
                Text(
                    text = "Password",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(32.dp, 4.dp, 20.dp, 0.dp)
                )
                CustomTextField(
                    value = password,
                    onValueChanged = { password = it },
                    text = "Password",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 2.dp)
                )

                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF5DA119))
                ) {
                    Text(text = "SAVE", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFFC6CEBE))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(Color(0xFFC6CEBE))
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SettingsPreview() {
    SettingsView()
}