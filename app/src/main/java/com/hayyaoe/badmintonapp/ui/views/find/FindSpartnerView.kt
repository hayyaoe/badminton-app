package com.hayyaoe.badmintonapp.ui.views.find

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.google.gson.Gson
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.BottomBar
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.model.People
import com.hayyaoe.badmintonapp.showToast
import com.hayyaoe.badmintonapp.viewmodel.home.FindSpartnerViewModel


class FindMatch : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var dataPeople by remember { mutableStateOf<List<People>>(emptyList()) }
            val jsonFile = assets.open("people.json")
            val jsonString = jsonFile.bufferedReader().use { it.readText() }
            val peopleList = Gson().fromJson(jsonString, Array<People>::class.java).toList()
            dataPeople = peopleList

            BadmintonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    FindSpartnerView(peopleList)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindSpartnerView(
    people: List<OtherUser>,
    navController: NavController,
    findSpartnerViewModel: FindSpartnerViewModel
) {

    var showDialog by remember { mutableStateOf(false) }
    var selectedData by remember { mutableStateOf<OtherUser?>(null) }

    @Composable
    fun PopupCardContent(data: OtherUser) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { showDialog = false
                    selectedData = null }
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CardDetailView(
                _name = data.username,
                _location = findSpartnerViewModel.getLocationById(data.location_id),
                _profilePicture = data.profile_path,
                _phone = data.phone_number,
                _instagram = data.contacts,
                onCardClick = {
                    showDialog = false
                    selectedData = null
                },

            )
        }
    }

    Box(
        modifier = Modifier
    ) {
        Scaffold(
            topBar = {
                TopBar("", true, navController)
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(12.dp),
                ) {

                    LazyColumn(
                        modifier = Modifier
                    ) {
                        item {
                            Text(
                                text = "Find \nSpartner",
                                lineHeight = 60.sp,
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                        }
                        people.forEach {
                            item {
                                CardFindView(
                                    _id = it.id,
                                    _rank = it.rank,
                                    _profilePicture = it.profile_path,
                                    _name = it.username,
                                    _location = it.location_id,
                                    _phone = it.phone_number,
                                    _instagram = it.contacts,
                                    onCardClick = {
                                        selectedData = it
                                        showDialog = true
                                    },
                                    findSpartnerViewModel= findSpartnerViewModel
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .shadow(elevation = 24.dp, ambientColor = Color.Black)
                ) {
                    BottomBar(navController)
                }

            }
        )

        if (showDialog) {
            Dialog(
                onDismissRequest = {
                    showDialog = false
                    selectedData = null
                }
            ) {
                PopupCardContent(selectedData!!)
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun FindMatchPreview() {

    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            FindMatchView()
        }
    }
}