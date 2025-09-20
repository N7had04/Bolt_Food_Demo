package com.example.boltfooddemo.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UserPreferences {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val AUTH_TOKEN = stringPreferencesKey("auth_token")

    suspend fun saveLoginState(context: Context, isLoggedIn: Boolean, token: String?) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = isLoggedIn
            if (token != null) prefs[AUTH_TOKEN] = token
        }
    }

    fun getLoginState(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[IS_LOGGED_IN] == true }

    fun getAuthToken(context: Context): Flow<String?> =
        context.dataStore.data.map { prefs -> prefs[AUTH_TOKEN] }
}