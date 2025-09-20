package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.domain.repository.BoltRepository
import com.example.boltfooddemo.utils.Resource

class GetMenuUseCase(private val boltRepository: BoltRepository) {
    suspend fun execute(id: Int): Resource<List<MenuItem>> {
        return boltRepository.getMenu(id)
    }
}