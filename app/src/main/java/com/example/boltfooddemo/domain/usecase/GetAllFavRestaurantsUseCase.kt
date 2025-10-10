package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.FavRestaurant
import com.example.boltfooddemo.domain.repository.BoltRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavRestaurantsUseCase(private val boltRepository: BoltRepository) {
    fun execute(): Flow<List<FavRestaurant>> {
        return boltRepository.getAllFavRestaurants()
    }
}