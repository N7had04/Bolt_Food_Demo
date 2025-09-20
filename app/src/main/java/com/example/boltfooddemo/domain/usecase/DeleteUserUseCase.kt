package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.domain.repository.BoltRepository

class DeleteUserUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(user: User) {
        boltRepository.deleteUser(user)
    }
}