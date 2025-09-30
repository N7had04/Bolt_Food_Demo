package com.example.boltfooddemo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boltfooddemo.data.model.CountryCode
import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.domain.usecase.GetAllPastOrdersUseCase
import com.example.boltfooddemo.domain.usecase.GetAllRestaurantsUseCase
import com.example.boltfooddemo.domain.usecase.GetMenuUseCase
import com.example.boltfooddemo.domain.usecase.LoadCountryCodesUseCase
import com.example.boltfooddemo.domain.usecase.SaveRestaurantUseCase
import com.example.boltfooddemo.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllRestaurantsUseCase: GetAllRestaurantsUseCase,
    private val getMenuUseCase: GetMenuUseCase,
    private val saveRestaurantUseCase: SaveRestaurantUseCase,
    private val getAllPastOrdersUseCase: GetAllPastOrdersUseCase,
    private val loadCountryCodesUseCase: LoadCountryCodesUseCase
): ViewModel() {

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants = _restaurants.asStateFlow()

    private val _menu = MutableStateFlow<List<MenuItem>>(emptyList())
    val menu = _menu.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _phoneText = MutableStateFlow<String>("")
    val phoneText = _phoneText.asStateFlow()

    private val _selectedCountryCode = MutableStateFlow<String>("+93")
    val selectedCountryCode = _selectedCountryCode.asStateFlow()

    private val _searchCountryCodeText = MutableStateFlow<String>("")
    val searchCountryCodeText = _searchCountryCodeText.asStateFlow()

    private val _passwordText = MutableStateFlow<String>("")
    val passwordText = _passwordText.asStateFlow()

    private val _nameText = MutableStateFlow<String>("")
    val nameText = _nameText.asStateFlow()

    private val _surnameText = MutableStateFlow<String>("")
    val surnameText = _surnameText.asStateFlow()

    private val _emailText = MutableStateFlow<String>("")
    val emailText = _emailText.asStateFlow()

    fun updatePhoneText(text: String) {
        _phoneText.value = text
    }

    fun updateSelectedCountryCode(code: String) {
        _selectedCountryCode.value = code
    }

    fun updateSearchCountryCodeText(text: String) {
        _searchCountryCodeText.value = text
    }

    fun updatePasswordText(text: String) {
        _passwordText.value = text
    }

    fun updateNameText(text: String) {
        _nameText.value = text
    }

    fun updateSurnameText(text: String) {
        _surnameText.value = text
    }

    fun updateEmailText(text: String) {
        _emailText.value = text
    }

    fun getAllRestaurants() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            val resource = getAllRestaurantsUseCase.execute()
            when (resource) {
                is Resource.Success -> {
                    _restaurants.value = resource.data ?: emptyList()
                    Log.i("MY TAG", "getAllRestaurants: ${resource.data}")
                }
                is Resource.Error -> {
                    _errorMessage.value = resource.message
                    Log.i("MY TAG", "getAllRestaurants: ${resource.message}")
                }
            }
            _isLoading.value = false
        }
    }

    fun getMenu(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            val resource = getMenuUseCase.execute(id)
            when (resource) {
                is Resource.Success -> {
                    _menu.value = resource.data ?: emptyList()
                    Log.i("MY TAG", "getMenu: ${resource.data}")
                }
                is Resource.Error -> {
                    _errorMessage.value = resource.message
                    Log.i("MY TAG", "getMenu: ${resource.message}")
                }
            }
            _isLoading.value = false
        }
    }

    fun saveRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            saveRestaurantUseCase.execute(restaurant)
        }
    }

    fun getAllPastOrders(): Flow<List<Restaurant>> {
        return getAllPastOrdersUseCase.execute()
    }

    fun loadCountryCodes(): List<CountryCode> {
        val list = loadCountryCodesUseCase.execute()
        Log.i("MY TAG", "loadCountryCodes: $list")
        return list
    }
}