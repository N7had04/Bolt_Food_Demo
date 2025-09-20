package com.example.boltfooddemo.data.repository

import android.content.Context
import com.example.boltfooddemo.data.datastore.UserPreferences
import com.example.boltfooddemo.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepositoryImpl(private val context: Context): UserPreferencesRepository  {
    override suspend fun saveLoginState(isLoggedIn: Boolean, token: String?) {
        UserPreferences.saveLoginState(context, isLoggedIn, token)
    }

    override fun getLoginState(): Flow<Boolean> {
        return UserPreferences.getLoginState(context)
    }

    override fun getAuthToken(): Flow<String?> {
        return UserPreferences.getAuthToken(context)
    }
}