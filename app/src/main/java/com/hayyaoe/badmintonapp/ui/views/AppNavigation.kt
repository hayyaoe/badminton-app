package com.hayyaoe.badmintonapp.ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector



sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Home : Screen("home", Icons.Outlined.Home, "Home")
    object Favorite : Screen("favorite", Icons.Outlined.FavoriteBorder, "Search")
    object Shop : Screen("shop", Icons.Outlined.ShoppingCart, "Profile")
    object Account : Screen("account", Icons.Outlined.AccountCircle, "Settings")
}