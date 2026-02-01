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
import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.presentation.ui.components.BottomNavigationBar
import com.example.boltfooddemo.presentation.utils.Screens

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    user: User,
    isFav: (Restaurant) -> Boolean,
    pastOrders: List<Restaurant>,
    restaurants: List<Restaurant>,
    favRestaurants: List<Restaurant>,
    onInsertOrDelete: (Restaurant) -> Unit,
    searchMenuText: String,
    onValueChange: (String) -> Unit,
    onNavigateToAllScreen: (String) -> Unit,
    onNavigateToInfoScreen: (Restaurant) -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                isSelected = { currentRoute == it },
                onNavigate = {
                    navController.navigate(it) {
                        popUpTo(currentRoute.toString()) {
                            inclusive = true
                        }
                    }
                }
            )
        }
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
                    onNavigateToAllScreen = {onNavigateToAllScreen(it)},
                    onNavigateToInfoScreen = {onNavigateToInfoScreen(it)},
                    onNavigateToSearchScreen = {navController.navigate(Screens.SearchScreen.route)},
                    onInsertOrDelete = {onInsertOrDelete(it)}
                )
            }
            composable(Screens.SearchScreen.route) {
                SearchScreen(
                    restaurants = restaurants,
                    isFav = isFav,
                    onInsertOrDelete = {onInsertOrDelete(it)},
                    onNavigateBack = {navController.navigate(Screens.HomeScreen.route)},
                    onNavigateToInfoScreen = {onNavigateToInfoScreen(it)},
                    searchMenuText = searchMenuText,
                    onValueChange = {onValueChange(it)}
                )
            }
            composable(Screens.OrderScreen.route) {
                OrderScreen(
                    pastOrders = pastOrders,
                    onNavigateToInfoScreen = {onNavigateToInfoScreen(it)}
                )
            }
            composable(Screens.AccountScreen.route) {
                AccountScreen(
                    user = user,
                    favRestaurants = favRestaurants,
                    onNavigateToInfoScreen = {onNavigateToInfoScreen(it)},
                    onNavigateToAllScreen = {onNavigateToAllScreen(it)},
                    onLogout = {onLogout()},
                    onDeleteAccount = {onDeleteAccount()}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        user = User(0, "", "", "", "", ""),
        isFav = {true},
        pastOrders = emptyList(),
        restaurants = emptyList(),
        favRestaurants = emptyList(),
        onInsertOrDelete = {},
        searchMenuText = "",
        onValueChange = {},
        onNavigateToAllScreen = {},
        onNavigateToInfoScreen = {},
        onLogout = {},
        onDeleteAccount = {}
    )
}