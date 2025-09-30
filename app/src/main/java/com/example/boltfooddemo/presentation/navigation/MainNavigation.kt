package com.example.boltfooddemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.ui.screens.AccountScreen
import com.example.boltfooddemo.presentation.ui.screens.HomeScreen
import com.example.boltfooddemo.presentation.ui.screens.OrderScreen
import com.example.boltfooddemo.presentation.ui.screens.SearchScreen
import com.example.boltfooddemo.presentation.ui.screens.StoreScreen
import com.example.boltfooddemo.presentation.utils.Screens

@Composable
fun MainNavigation(
    modifier: Modifier,
    navController: NavHostController,
    pastOrders: List<Restaurant>
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = modifier
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                pastOrders = pastOrders
            )
        }
        composable(Screens.StoreScreen.route) {
            StoreScreen()
        }
        composable(Screens.SearchScreen.route) {
            SearchScreen()
        }
        composable(Screens.OrderScreen.route) {
            OrderScreen()
        }
        composable(Screens.AccountScreen.route) {
            AccountScreen()
        }
    }
}