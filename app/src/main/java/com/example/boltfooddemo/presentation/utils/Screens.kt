package com.example.boltfooddemo.presentation.utils

sealed class Screens(val route: String) {
    object Splashscreen: Screens("splash_screen")
    object PhoneScreen: Screens("phone_screen")
    object PasswordScreen: Screens("password_screen")
    object CountryCodesScreen: Screens("country_codes_screen")
    object MainScreen: Screens("main_screen")
    object RegistrationScreen: Screens("registration_screen")
    object HomeScreen: Screens("home_screen")
    object SearchScreen: Screens("search_screen")
    object OrderScreen: Screens("order_screen")
    object AccountScreen: Screens("account_screen")
    object AllScreen: Screens("all_screen")
    object InfoScreen: Screens("info_screen")
    object PaymentScreen: Screens("payment_screen")
}