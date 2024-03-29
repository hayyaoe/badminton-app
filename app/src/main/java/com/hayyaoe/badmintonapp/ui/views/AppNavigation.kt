package com.hayyaoe.badmintonapp.ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.hayyaoe.badmintonapp.ui.ListScreen


sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Home : Screen("home", Icons.Outlined.Home, "Home")
    object Favorite : Screen(ListScreen.FriendRequestView.name, Icons.Outlined.FavoriteBorder, "Search")
    object Shop : Screen(ListScreen.FriendsView.name, Icons.Outlined.MailOutline, "Profile")
    object Account : Screen(ListScreen.SettingsView.name, Icons.Outlined.AccountCircle, "Settings")
}