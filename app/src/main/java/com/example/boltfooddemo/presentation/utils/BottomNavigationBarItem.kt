package com.example.boltfooddemo.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationBarItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Home : BottomNavigationBarItem("Home", Icons.Default.Home, Screens.HomeScreen.route)
    object Search : BottomNavigationBarItem("Search", Icons.Default.Search, Screens.SearchScreen.route)
    object Order : BottomNavigationBarItem("Orders", Icons.AutoMirrored.Filled.Notes, Screens.OrderScreen.route)
    object Account : BottomNavigationBarItem("Account", Icons.Default.AccountCircle, Screens.AccountScreen.route)
}
