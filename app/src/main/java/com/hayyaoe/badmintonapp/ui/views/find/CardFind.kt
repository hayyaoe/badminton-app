package com.hayyaoe.badmintonapp.ui.views.find

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hayyaoe.badmintonapp.getResId
import com.hayyaoe.badmintonapp.model.OtherUser
import com.hayyaoe.badmintonapp.model.People
import com.hayyaoe.badmintonapp.model.Spartner
import com.hayyaoe.badmintonapp.repository.BadmintonContainer
import com.hayyaoe.badmintonapp.ui.theme.BadmintonAppTheme
import com.hayyaoe.badmintonapp.viewmodel.home.FindSpartnerViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.SpartnerRequestViewModel
import com.hayyaoe.badmintonapp.viewmodel.home.SpartnersViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFindView(
    _id : Int,
    _rank: Int,
    _profilePicture: String?,
    _name: String,
    _location: Int,
    _phone: String,
    _instagram: String,
    onCardClick: (OtherUser) -> Unit,
    findSpartnerViewModel: FindSpartnerViewModel,
    iconOnClick: ()->Unit
){

    val context = LocalContext.current
    val loc = findSpartnerViewModel.getLocationById(_location)
    val profilePicture: Int = getResId(_profilePicture)
    val name: String = _name
    val location: String = loc
    val phone: String = _phone
    val instagram: String = _instagram

    Card(
        onClick = {
            // Create a People object with relevant data
            val person = OtherUser(username = _name,profile_path = _profilePicture, location_id = _location, phone_number = _phone, contacts =  _instagram, rank = _rank, id = _id)
            // Call the callback with the People object
            onCardClick(person)
        },
        modifier = Modifier
            .padding(12.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff5DA118)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                contentAlignment = Alignment.CenterEnd,
            ){
                IconButton(onClick = iconOnClick) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "add",
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp),
                        tint = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    model = ImageRequest.Builder(context).data(BadmintonContainer.API_URL+_profilePicture ).crossfade(true).build(),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(9.dp)),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = name,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        softWrap = true,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(end =60.dp),
                    )
                    Text(
                        text = location,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        softWrap = true,
                        maxLines = 2,
                        lineHeight = 15.sp,
                        modifier = Modifier
                            .padding(end = 60.dp),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardRequestView(
    _id : Int,
    _rank: Int,
    _profilePicture: String?,
    _name: String,
    _location: Int,
    _phone: String,
    _instagram: String,
    onCardClick: (OtherUser) -> Unit,
    spartnerRequestViewModel: SpartnerRequestViewModel,
    iconOnClick: ()->Unit
){

    val context = LocalContext.current
    val loc = spartnerRequestViewModel.getLocationById(_location)
    val profilePicture: Int = getResId(_profilePicture)
    val name: String = _name
    val location: String = loc
    val phone: String = _phone
    val instagram: String = _instagram

    Card(
        onClick = {
            // Create a People object with relevant data
            val person = OtherUser(username = _name,profile_path = _profilePicture, location_id = _location, phone_number = _phone, contacts =  _instagram, rank = _rank, id = _id)
            // Call the callback with the People object
            onCardClick(person)
        },
        modifier = Modifier
            .padding(12.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff5DA118)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                contentAlignment = Alignment.CenterEnd,
            ){
                IconButton(onClick = iconOnClick) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "add",
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp),
                        tint = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    model = ImageRequest.Builder(context).data(BadmintonContainer.API_URL+_profilePicture ).crossfade(true).build(),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(9.dp)),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = name,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        softWrap = true,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(end =60.dp),
                    )
                    Text(
                        text = location,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        softWrap = true,
                        maxLines = 2,
                        lineHeight = 15.sp,
                        modifier = Modifier
                            .padding(end = 60.dp),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpartnerCardView(
    _id : Int,
    _rank: Int,
    _profilePicture: String?,
    _name: String,
    _location: Int,
    _phone: String,
    _instagram: String,
    onCardClick: (OtherUser) -> Unit,
    spartnerViewModel: SpartnersViewModel,
    iconOnClick: ()->Unit
){

    val context = LocalContext.current
    val loc = spartnerViewModel.getLocationById(_location)
    val profilePicture: String
    val name: String = _name
    val location: String = loc
    val phone: String = _phone
    val instagram: String = _instagram

    Card(
        onClick = {
            // Create a People object with relevant data
            val person = OtherUser(username = _name,profile_path = _profilePicture, location_id = _location, phone_number = _phone, contacts =  _instagram, rank = _rank, id = _id)
            // Call the callback with the People object
            onCardClick(person)
        },
        modifier = Modifier
            .padding(12.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff5DA118)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                contentAlignment = Alignment.CenterEnd,
            ){
                IconButton(onClick = iconOnClick) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "add",
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp),
                        tint = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    model = ImageRequest.Builder(context).data(BadmintonContainer.API_URL+_profilePicture ).crossfade(true).build(),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .width(128.dp)
                        .height(128.dp)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(9.dp)),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = name,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        softWrap = true,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(end =60.dp),
                    )
                    Text(
                        text = location,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        softWrap = true,
                        maxLines = 2,
                        lineHeight = 15.sp,
                        modifier = Modifier
                            .padding(end = 60.dp),
                    )
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
fun CardFindPreview() {
    BadmintonAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CardFindView(_id = 1, _rank = 200,"rafi", "Rafi Abhista Naya WP, Citraland UC", 1, "091234567890", "abhista_naya", {},viewModel(),{})
        }
    }
}