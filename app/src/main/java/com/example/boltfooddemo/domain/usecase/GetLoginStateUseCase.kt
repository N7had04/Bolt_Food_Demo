package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetLoginStateUseCase(private val userPreferencesRepository: UserPreferencesRepository) {
    fun execute(): Flow<Boolean> {
        return userPreferencesRepository.getLoginState()
    }
}