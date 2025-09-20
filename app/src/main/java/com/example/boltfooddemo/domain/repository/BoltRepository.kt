package com.example.boltfooddemo.domain.repository

import com.example.boltfooddemo.data.model.CountryCode
import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.utils.Resource

interface BoltRepository {
    suspend fun getAllRestaurants(): Resource<List<Restaurant>>
    suspend fun getMenu(id: Int): Resource<List<MenuItem>>
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getUserByPhone(phone: String): User?
    fun loadCountryCodes(): List<CountryCode>
}