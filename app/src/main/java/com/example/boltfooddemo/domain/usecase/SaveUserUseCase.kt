package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.domain.repository.BoltRepository

class SaveUserUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(user: User) {
        boltRepository.insertUser(user)
    }
}