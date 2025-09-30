package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.domain.repository.BoltRepository
import kotlinx.coroutines.flow.Flow

class GetAllPastOrdersUseCase(private val boltRepository: BoltRepository) {
    fun execute(): Flow<List<Restaurant>> {
        return boltRepository.getAllPastOrders()
    }
}