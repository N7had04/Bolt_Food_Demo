package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.presentation.utils.Screens

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    isFav: (Restaurant) -> Boolean,
    pastOrders: List<Restaurant>,
    restaurants: List<Restaurant>,
    onInsertOrDelete: (Restaurant) -> Unit
) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(
            isSelected = { currentRoute == it },
            onNavigate = { navController.navigate(it) {
                popUpTo(currentRoute.toString()) {
                    inclusive = true
                }
            } }
        ) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.route,
            modifier = modifier.padding(padding)
        ) {
            composable(Screens.HomeScreen.route) {
                HomeScreen(
                    pastOrders = pastOrders,
                    restaurants = restaurants,
                    isFav = isFav,
                    onNavigateToAllScreen = {},
                    onNavigateToInfoScreen = {},
                    onInsertOrDelete = {onInsertOrDelete(it)}
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
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        isFav = {true},
        pastOrders = emptyList(),
        restaurants = emptyList(),
        onInsertOrDelete = {}
    )
}