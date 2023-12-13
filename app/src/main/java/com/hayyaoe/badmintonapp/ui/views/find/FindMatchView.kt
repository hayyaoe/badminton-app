package com.hayyaoe.badmintonapp.ui.views.find

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.gson.Gson
import com.hayyaoe.badmintonapp.navController
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.BottomBar
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.model.People
import com.hayyaoe.badmintonapp.showToast


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
                    FindMatchView(peopleList)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindMatchView(people: List<People>) {

    val context = LocalContext.current
    val navController = navController()

    var showDialog by remember { mutableStateOf(false) }
    var selectedData by remember { mutableStateOf<People?>(null) }

    @Composable
    fun PopupCardContent(data: People) {
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
                _name = data.name,
                _location = data.location,
                _profilePicture = data.profilePicture,
                _phone = data.phone,
                _instagram = data.instagram,
                onCardClick = {
                    showDialog = false
                    selectedData = null
                }

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
                                    _profilePicture = it.profilePicture,
                                    _name = it.name,
                                    _location = it.location,
                                    _phone = it.phone,
                                    _instagram = it.instagram,
                                    onCardClick = {
                                        showToast(context, it.name)
                                        selectedData = it
                                        showDialog = true
                                    }
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