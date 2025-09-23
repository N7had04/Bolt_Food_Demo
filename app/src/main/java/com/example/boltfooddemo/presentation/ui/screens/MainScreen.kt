package com.example.boltfooddemo.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boltfooddemo.presentation.navigation.MainNavigation

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
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
        MainNavigation(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}