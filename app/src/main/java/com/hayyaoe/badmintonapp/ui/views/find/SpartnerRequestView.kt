package com.hayyaoe.badmintonapp.ui.views.find

import android.content.res.Configuration
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
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.model.SpartnerRequest
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.ui.views.BottomBar
import com.hayyaoe.badmintonapp.ui.views.TopBar
import com.hayyaoe.badmintonapp.viewmodel.home.FindSpartnerViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.SpartnerRequestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpartnerRequestView(
    people: List<SpartnerRequest>,
    navController: NavController,
    spartnerRequestViewModel: SpartnerRequestViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedData by remember { mutableStateOf<OtherUser?>(null) }

    @Composable
    fun PopupCardContent(data: OtherUser) {
        val context = LocalContext.current
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
                _location = spartnerRequestViewModel.getLocationById(data.location_id),
                _profilePicture = data.profile_path,
                _phone = data.phone_number,
                _instagram = data.contacts,
                onCardClick = {
                    selectedData = null
                },
                context = context

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
                                text = "Spartner \nRequests",
                                lineHeight = 60.sp,
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                            )
                        }
                        people.forEach {
                            item {
                                CardRequestView(
                                    _id = it.user1data.id,
                                    _rank = it.user1data.rank,
                                    _profilePicture = it.user1data.image_path,
                                    _name = it.user1data.username,
                                    _location = 1,
                                    _phone = "",
                                    _instagram ="",
                                    onCardClick = {
                                        selectedData = it
                                    },
                                    spartnerRequestViewModel= spartnerRequestViewModel,
                                    iconOnClick = {spartnerRequestViewModel.spartnerUpdate(it.id)}
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
fun SpartnerRequestPreview() {

    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            FindMatchView()
        }
    }
}