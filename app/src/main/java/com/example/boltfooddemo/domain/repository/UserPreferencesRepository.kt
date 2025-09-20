package com.example.boltfooddemo.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun saveLoginState(isLoggedIn: Boolean, token: String?)
    fun getLoginState(): Flow<Boolean>
    fun getAuthToken(): Flow<String?>
}