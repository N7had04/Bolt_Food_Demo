package com.example.boltfooddemo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.domain.usecase.DeleteUserUseCase
import com.example.boltfooddemo.domain.usecase.GetAuthTokenUseCase
import com.example.boltfooddemo.domain.usecase.GetLoginStateUseCase
import com.example.boltfooddemo.domain.usecase.GetUserUseCase
import com.example.boltfooddemo.domain.usecase.SaveLoginStateUseCase
import com.example.boltfooddemo.domain.usecase.SaveUserUseCase
import com.example.boltfooddemo.domain.usecase.UpdateUserUseCase
import com.example.boltfooddemo.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val saveLoginStateUseCase: SaveLoginStateUseCase,
    private val getLoginStateUseCase: GetLoginStateUseCase,
    private val getAuthTokenUseCase: GetAuthTokenUseCase
): ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    val isLoggedIn = getLoginState()

    fun signUp(phone: String, password: String, name: String, lastName: String, email: String) {
        viewModelScope.launch {
            saveUserUseCase.execute(User(phone = phone, password = password, name = name, lastname = lastName, email = email))
            saveLoginStateUseCase.execute(true, phone)
            Log.i("MY TAG", "Insert user: $phone $password $name $lastName $email")
            Log.i("MY TAG", "Login state: ${getLoginStateUseCase.execute()}, Auth token: ${getAuthTokenUseCase.execute()}")
        }
    }

    fun login(password: String): Resource<User> {
        if (_user.value?.password == password) {
            viewModelScope.launch {
                saveLoginStateUseCase.execute(true, _user.value?.phone)
                Log.i("MY TAG", "Login state: ${getLoginStateUseCase.execute()}, Auth token: ${getAuthTokenUseCase.execute()}")
            }
            return Resource.Success(_user.value ?: User(0, "", "", "", "", ""))
        } else {
            Log.i("MY TAG", "Login failed")
            return Resource.Error("Password is incorrect")
        }
    }

    fun logout() {
        viewModelScope.launch {
            saveLoginStateUseCase.execute(false, null)
            Log.i("MY TAG", "Login state: ${getLoginStateUseCase.execute()}, Auth token: ${getAuthTokenUseCase.execute()}")
        }
    }

    fun getUser(phone: String) {
        viewModelScope.launch {
            _user.value = getUserUseCase.execute(phone)
            Log.i("MY TAG", "Get user: ${_user.value}")
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            updateUserUseCase.execute(_user.value ?: User(0, "", "", "", "", ""))
        }
    }

    fun deleteUser() {
        viewModelScope.launch {
            deleteUserUseCase.execute(_user.value ?: User(0, "", "", "", "", ""))
            Log.i("MY TAG", "Delete user: ${_user.value}")
        }
    }

    fun getLoginState() = getLoginStateUseCase.execute()

    fun getAuthToken() = getAuthTokenUseCase.execute()
}