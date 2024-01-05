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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hayyaoe.badmintonapp.R
import com.hayyaoe.badmintonapp.data.DataStoreManager
import com.hayyaoe.badmintonapp.model.UserData
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.views.auth.CustomButton
import com.hayyaoe.badmintonapp.ui.views.auth.CustomDropdownMenuBox
import com.hayyaoe.badmintonapp.ui.views.auth.CustomFileSelectionBox
import com.hayyaoe.badmintonapp.ui.views.auth.CustomTextBox
import com.hayyaoe.badmintonapp.ui.views.auth.poppins
import com.hayyaoe.badmintonapp.viewmodel.home.SettingsViewModel

@Composable
fun SettingsView(
    settingsViewModel: SettingsViewModel,
    navController: NavController,
    dataStore: DataStoreManager,
    userData: UserData,
    regions: List<String>
) {
    var username by rememberSaveable { mutableStateOf(userData.username) }
    var profilePicture by rememberSaveable { mutableStateOf(userData.image_path) }
    var region by rememberSaveable { mutableStateOf(if (userData.location_id != null) regions[userData.location_id-1] else "Select Region") }
    var phone by rememberSaveable { mutableStateOf(if(userData.phone_number.isNullOrBlank()) "" else userData.phone_number) }
    var otherContact by rememberSaveable { mutableStateOf(if (userData.contacts.isNullOrBlank()) "" else userData.contacts) }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var rank by rememberSaveable { mutableStateOf(userData.rank) }

    val context = LocalContext.current

    var selectedImage by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        selectedImage = it
        if (selectedImage != null){
            settingsViewModel.uploadImage(selectedImage!!, context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF1F1F1))
            .padding(0.dp, 32.dp, 0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(BadmintonContainer.API_URL+userData.image_path ).crossfade(true).build(),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(shape = CircleShape)
        )
        Row (
            modifier = Modifier.padding(bottom = 24.dp, top = 10.dp)
        ) {
            Text(
                text = userData.username,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(4.dp, 0.dp),
                fontFamily = poppins
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
                    .padding(vertical =  16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Edit Your Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(48.dp, 28.dp, 48.dp, 2.dp),
                    fontFamily = poppins,
                )
                CustomFileSelectionBox(value = "", onValueChange = {selectedImage = it}, label = "New Profile Picture") {
                    galleryLauncher.launch("image/*")
                }

                CustomDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it },
                    value = region,
                    onValueChange = { region = it },
                    isError = false,
                    options = regions,
                    label = "Region"
                )
                CustomTextBox(
                    value = username,
                    onValueChange = {username = it},
                    label = "Username",
                    errorMessage = "Cannot Be Null")
                CustomTextBox(
                    value = phone,
                    onValueChange = {phone = it},
                    label = "Phone",
                    errorMessage = ""
                )
                CustomTextBox(
                    value = otherContact,
                    onValueChange = {otherContact = it},
                    label = "Contacts", errorMessage = ""
                )

                CustomTextBox(
                    password,
                    { password = it },
                    "Password",
                    KeyboardType.Password,
                    isPassword = true,
                    isError = settingsViewModel.isPasswordCorrect(password, confirmPassword),
                    errorMessage = "Password Don't Match"

                )
                CustomTextBox(
                    confirmPassword,
                    { confirmPassword = it },
                    "Confirm Password",
                    KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    isPassword = true,
                    isError = settingsViewModel.isPasswordCorrect(password, confirmPassword),
                    errorMessage = "Password Don't Match"
                )

                CustomButton(
                    onClick = {
                        settingsViewModel.updateUser(username = username,region = region, phone_number = phone, contacts = otherContact, password, rank = rank, image_path = profilePicture)
                        },
                    content = "SAVE",
                    modifier = Modifier.padding(vertical = 26.dp, horizontal = 26.dp),
                    isEnabled = region.isNotBlank() && otherContact.isNotBlank() && phone.isNotBlank() && username.isNotBlank())
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
//    SettingsView(viewModel(), navController(), DataStoreManager(LocalContext.current), UserData())
}