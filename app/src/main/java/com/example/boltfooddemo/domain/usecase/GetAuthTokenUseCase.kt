package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetAuthTokenUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    fun execute(): Flow<String?> {
        return userPreferencesRepository.getAuthToken()
    }
}