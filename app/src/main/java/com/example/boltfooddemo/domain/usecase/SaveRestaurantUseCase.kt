package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.domain.repository.BoltRepository

class SaveRestaurantUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(restaurant: Restaurant) {
        boltRepository.insertRestaurant(restaurant)
    }
}