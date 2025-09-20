package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.domain.repository.BoltRepository
import com.example.boltfooddemo.utils.Resource

class GetAllRestaurantsUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(): Resource<List<Restaurant>> {
        return boltRepository.getAllRestaurants()
    }
}