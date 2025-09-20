package com.example.boltfooddemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.boltfooddemo.presentation.ui.screens.CountryCodesScreen
import com.example.boltfooddemo.presentation.ui.screens.MainScreen
import com.example.boltfooddemo.presentation.ui.screens.PasswordScreen
import com.example.boltfooddemo.presentation.ui.screens.PhoneScreen
import com.example.boltfooddemo.presentation.ui.screens.RegistrationScreen
import com.example.boltfooddemo.presentation.ui.screens.SplashScreen
import com.example.boltfooddemo.presentation.utils.Screens
import com.example.boltfooddemo.presentation.viewmodel.AuthViewModel
import com.example.boltfooddemo.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = koinViewModel(),
    authViewModel: AuthViewModel = koinViewModel()
) {
    val user = authViewModel.user.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screens.Splashscreen.route
    ) {
        composable(Screens.Splashscreen.route) {
            SplashScreen(
                onNavigate = {
                    navController.navigate(Screens.PhoneScreen.route) {
                        popUpTo(Screens.Splashscreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screens.PhoneScreen.route) {
            PhoneScreen(
                selectedCountryCode = mainViewModel.selectedCountryCode.value,
                phoneText = mainViewModel.phoneText.value,
                onValueChange = { mainViewModel.updatePhoneText(it) },
                onNavigateToPasswordScreen = {
                    navController.navigate(Screens.PasswordScreen.route)
                },
                onNavigateToCountryCodesScreen = {
                    navController.navigate(Screens.CountryCodesScreen.route)
                }
            )
        }
        composable(Screens.PasswordScreen.route) {
            val phone = mainViewModel.selectedCountryCode.value + mainViewModel.phoneText.value
            LaunchedEffect(phone) {
                authViewModel.getUser(phone)
            }
            PasswordScreen(
                isRegistration = user.value == null,
                onValueChange = { mainViewModel.updatePasswordText(it) },
                onNavigateBack = { navController.popBackStack() },
                onNavigate = {
                    if (user.value == null) {
                        navController.navigate(Screens.RegistrationScreen.route) {
                            popUpTo(Screens.PasswordScreen.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        authViewModel.login(mainViewModel.passwordText.value)
                        navController.navigate(Screens.MainScreen.route) {
                            popUpTo(Screens.PasswordScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }
        composable(Screens.CountryCodesScreen.route) {
            val countryCodes = mainViewModel.loadCountryCodes()
            CountryCodesScreen(
                countryCodes = countryCodes,
                searchCountryCodeText = mainViewModel.searchCountryCodeText.value,
                onValueChange = { mainViewModel.updateSearchCountryCodeText(it) },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPhoneScreen = { dialCode ->
                    mainViewModel.updateSelectedCountryCode(dialCode)
                    navController.popBackStack()
                }
            )
        }
        composable(Screens.MainScreen.route) {
            MainScreen()
        }
        composable(Screens.RegistrationScreen.route) {
            RegistrationScreen()
        }
    }
}