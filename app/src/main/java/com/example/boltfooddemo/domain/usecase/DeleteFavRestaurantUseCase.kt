package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.FavRestaurant
import com.example.boltfooddemo.domain.repository.BoltRepository

class DeleteFavRestaurantUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(favRestaurant: FavRestaurant) {
        boltRepository.deleteFavRestaurant(favRestaurant)
    }
}