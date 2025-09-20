package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.domain.repository.UserPreferencesRepository

class SaveLoginStateUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    suspend fun execute(isLoggedIn: Boolean, token: String?) {
        userPreferencesRepository.saveLoginState(isLoggedIn, token)
    }
}