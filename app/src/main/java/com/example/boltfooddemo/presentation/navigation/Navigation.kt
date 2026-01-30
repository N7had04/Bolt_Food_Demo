package com.example.boltfooddemo.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.boltfooddemo.R
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.data.utils.favRestaurantToRestaurant
import com.example.boltfooddemo.data.utils.restaurantToFavRestaurant
import com.example.boltfooddemo.presentation.ui.screens.AllScreen
import com.example.boltfooddemo.presentation.ui.screens.CountryCodesScreen
import com.example.boltfooddemo.presentation.ui.screens.InfoScreen
import com.example.boltfooddemo.presentation.ui.screens.MainScreen
import com.example.boltfooddemo.presentation.ui.screens.PasswordScreen
import com.example.boltfooddemo.presentation.ui.screens.PaymentScreen
import com.example.boltfooddemo.presentation.ui.screens.PhoneScreen
import com.example.boltfooddemo.presentation.ui.screens.RegistrationScreen
import com.example.boltfooddemo.presentation.ui.screens.SplashScreen
import com.example.boltfooddemo.presentation.utils.Screens
import com.example.boltfooddemo.presentation.viewmodel.AuthViewModel
import com.example.boltfooddemo.presentation.viewmodel.MainViewModel
import com.example.boltfooddemo.utils.Resource
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = koinViewModel(),
    authViewModel: AuthViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val user = authViewModel.user.collectAsState()

    val phoneText = mainViewModel.phoneText.collectAsState()
    val selectedCountryCode = mainViewModel.selectedCountryCode.collectAsState()
    val searchCountryCodeText = mainViewModel.searchCountryCodeText.collectAsState()
    val searchText = mainViewModel.searchText.collectAsState()
    val searchMenuText = mainViewModel.searchMenuText.collectAsState()
    val passwordText = mainViewModel.passwordText.collectAsState()
    val nameText = mainViewModel.nameText.collectAsState()
    val surnameText = mainViewModel.surnameText.collectAsState()
    val emailText = mainViewModel.emailText.collectAsState()
    val pastOrders = mainViewModel.getAllPastOrders().collectAsState(initial = emptyList())
    val favRestaurants = mainViewModel.getAllFavRestaurants().collectAsState(initial = emptyList())
    val restaurants = mainViewModel.restaurants.collectAsState()

    val phone = selectedCountryCode.value + phoneText.value
    val isLoggedIn = authViewModel.isLoggedIn.collectAsState(initial = false)

    NavHost(
        navController = navController,
        startDestination = Screens.Splashscreen.route
    ) {
        composable(Screens.Splashscreen.route) {
            SplashScreen(
                onNavigate = {
                    if (isLoggedIn.value) {
                        navController.navigate(Screens.MainScreen.route) {
                            popUpTo(Screens.Splashscreen.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(Screens.PhoneScreen.route) {
                            popUpTo(Screens.Splashscreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }

        composable(Screens.PhoneScreen.route) {
            PhoneScreen(
                selectedCountryCode = selectedCountryCode.value,
                phoneText = phoneText.value,
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
            LaunchedEffect(phone) {
                authViewModel.getUser(phone)
            }
            PasswordScreen(
                isRegistration = user.value == null,
                passwordText = passwordText.value,
                onValueChange = { mainViewModel.updatePasswordText(it) },
                onNavigateBack = { navController.popBackStack() },
                onNavigate = {
                    if (user.value == null) {
                        navController.navigate(Screens.RegistrationScreen.route)
                    } else {
                        val loginResult = authViewModel.login(passwordText.value)
                        if (loginResult is Resource.Success) {
                            navController.navigate(Screens.MainScreen.route) {
                                popUpTo(Screens.PasswordScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(context, loginResult.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }

        composable(Screens.CountryCodesScreen.route) {
            val countryCodes = mainViewModel.loadCountryCodes()
            CountryCodesScreen(
                countryCodes = countryCodes,
                searchCountryCodeText = searchCountryCodeText.value,
                onValueChange = { mainViewModel.updateSearchCountryCodeText(it) },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPhoneScreen = { dialCode ->
                    mainViewModel.updateSelectedCountryCode(dialCode)
                    navController.popBackStack()
                }
            )
        }

        composable(Screens.RegistrationScreen.route) {
            RegistrationScreen(
                name = nameText.value,
                onNameChange = { mainViewModel.updateNameText(it) },
                surname = surnameText.value,
                onSurnameChange = { mainViewModel.updateSurnameText(it) },
                email = emailText.value,
                onEmailChange = { mainViewModel.updateEmailText(it) },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMainScreen = {
                    authViewModel.signUp(
                        phone,
                        passwordText.value,
                        nameText.value,
                        surnameText.value,
                        emailText.value
                    )
                    authViewModel.getUser(phone)
                    navController.navigate(Screens.MainScreen.route) {
                        popUpTo(Screens.RegistrationScreen.route) {
                            inclusive = true
                        }
                        popUpTo(Screens.PasswordScreen.route) {
                            inclusive = true
                        }
                        popUpTo(Screens.PhoneScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screens.MainScreen.route) {
            LaunchedEffect(Unit) {
                mainViewModel.getAllRestaurants()
            }
            MainScreen(
                isFav = {restaurant -> favRestaurants.value.any { it.restaurantID == restaurant.restaurantID }},
                pastOrders = pastOrders.value,
                restaurants = restaurants.value,
                onInsertOrDelete = {restaurant ->
                    val favRestaurant = favRestaurants.value.firstOrNull { it.restaurantID == restaurant.restaurantID }
                    if (favRestaurant != null) {
                        mainViewModel.deleteFavRestaurant(favRestaurant)
                    } else {
                        mainViewModel.saveFavRestaurant(restaurantToFavRestaurant(restaurant))
                    }
                },
                searchMenuText = searchMenuText.value,
                onValueChange = { mainViewModel.updateSearchMenuText(it) },
                onNavigateToAllScreen = { text -> navController.navigate(Screens.AllScreen.route + "/$text") },
                onNavigateToInfoScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("restaurant", it)
                    navController.navigate(Screens.InfoScreen.route)
                }
            )
        }

        composable(Screens.AllScreen.route + "/{text}", arguments = listOf(
            navArgument("text") {
                type = androidx.navigation.NavType.StringType
            }
        )) { it ->
            val text = it.arguments?.getString("text") ?: ""

            AllScreen(
                restaurants = if (text == stringResource(R.string.order_again)) pastOrders.value else favRestaurants.value.map { restaurant ->
                    favRestaurantToRestaurant(restaurant)
                },
                text = text,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToInfoScreen = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("restaurant", it)
                    navController.navigate(Screens.InfoScreen.route)
                },
                isFav = {restaurant -> favRestaurants.value.any { favRestaurant ->
                    favRestaurant.restaurantID == restaurant.restaurantID
                } },
                onInsertOrDelete = { restaurant ->
                    val favRestaurant = favRestaurants.value.firstOrNull { favRestaurant ->
                        favRestaurant.restaurantID == restaurant.restaurantID
                    }
                    if (favRestaurant != null) {
                        mainViewModel.deleteFavRestaurant(favRestaurant)
                    } else {
                        mainViewModel.saveFavRestaurant(restaurantToFavRestaurant(restaurant))
                    }
                }
            )
        }

        composable(Screens.InfoScreen.route) {
            val restaurant = navController.previousBackStackEntry?.savedStateHandle?.get<Restaurant>("restaurant") ?: Restaurant(0, "", "", "", "")
            LaunchedEffect(Unit) {
                mainViewModel.getMenu(restaurant.restaurantID)
            }
            val menu = mainViewModel.menu.collectAsState()

            InfoScreen(
                restaurant = restaurant,
                menu = menu.value,
                isFav = favRestaurants.value.any { it.restaurantID == restaurant.restaurantID },
                searchText = searchText.value,
                onValueChange = { mainViewModel.updateSearchText(it) },
                onInsertOrDelete = {
                    val favRestaurant = favRestaurants.value.firstOrNull { it.restaurantID == restaurant.restaurantID }
                    if (favRestaurant != null) {
                        mainViewModel.deleteFavRestaurant(favRestaurant)
                    } else {
                        mainViewModel.saveFavRestaurant(restaurantToFavRestaurant(restaurant))
                    }
                },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPaymentScreen = { restaurant, totalCost ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("restaurant", restaurant)
                    navController.navigate(Screens.PaymentScreen.route + "/$totalCost") {
                        popUpTo(Screens.InfoScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screens.PaymentScreen.route + "/{totalCost}", arguments = listOf(
            navArgument("totalCost") {
                type = androidx.navigation.NavType.StringType
            })
        ) { backStackEntry ->
            val restaurant = navController.previousBackStackEntry?.savedStateHandle?.get<Restaurant>("restaurant") ?: Restaurant(0, "", "", "", "")
            val totalCost = backStackEntry.arguments?.getString("totalCost") ?: ""

            PaymentScreen(
                totalCost = totalCost,
                restaurant = restaurant,
                onNavigateBack = { navController.popBackStack() },
                saveOrder = { mainViewModel.saveRestaurant(restaurant) }
            )
        }
    }
}
