package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.domain.repository.BoltRepository

class GetUserUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(phone: String): User? {
        return boltRepository.getUserByPhone(phone)
    }
}