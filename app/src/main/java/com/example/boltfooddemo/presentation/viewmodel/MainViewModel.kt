package com.example.boltfooddemo.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boltfooddemo.data.model.CountryCode
import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.domain.usecase.GetAllRestaurantsUseCase
import com.example.boltfooddemo.domain.usecase.GetMenuUseCase
import com.example.boltfooddemo.domain.usecase.LoadCountryCodesUseCase
import com.example.boltfooddemo.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllRestaurantsUseCase: GetAllRestaurantsUseCase,
    private val getMenuUseCase: GetMenuUseCase,
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

    val phoneText = mutableStateOf("")
    val selectedCountryCode = mutableStateOf("+93")
    val searchCountryCodeText = mutableStateOf("")
    val passwordText = mutableStateOf("")

    fun updatePhoneText(text: String) {
        phoneText.value = text
    }

    fun updateSelectedCountryCode(code: String) {
        selectedCountryCode.value = code
    }

    fun updateSearchCountryCodeText(text: String) {
        searchCountryCodeText.value = text
    }

    fun updatePasswordText(text: String) {
        passwordText.value = text
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

    fun loadCountryCodes(): List<CountryCode> {
        val list = loadCountryCodesUseCase.execute()
        Log.i("MY TAG", "loadCountryCodes: $list")
        return list
    }
}