package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.domain.repository.BoltRepository

class SaveMenuItemUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(menuItem: MenuItem) {
        boltRepository.insertRestaurant(menuItem)
    }
}