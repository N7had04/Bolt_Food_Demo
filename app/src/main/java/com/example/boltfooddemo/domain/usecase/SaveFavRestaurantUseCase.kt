package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.FavRestaurant
import com.example.boltfooddemo.domain.repository.BoltRepository

class SaveFavRestaurantUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(favRestaurant: FavRestaurant) {
        boltRepository.insertFavRestaurant(favRestaurant)
    }
}