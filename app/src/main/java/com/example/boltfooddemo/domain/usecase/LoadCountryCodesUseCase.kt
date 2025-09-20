package com.example.boltfooddemo.domain.usecase

import com.example.boltfooddemo.data.model.CountryCode
import com.example.boltfooddemo.domain.repository.BoltRepository

class LoadCountryCodesUseCase(private val boltRepository: BoltRepository) {
    fun execute(): List<CountryCode> {
        return boltRepository.loadCountryCodes()
    }
}