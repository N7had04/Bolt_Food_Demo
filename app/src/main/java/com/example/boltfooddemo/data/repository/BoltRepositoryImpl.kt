package com.example.boltfooddemo.data.repository

import android.content.Context
import androidx.compose.runtime.collectAsState
import com.example.boltfooddemo.R
import com.example.boltfooddemo.data.db.RestaurantDao
import com.example.boltfooddemo.data.db.UserDao
import com.example.boltfooddemo.data.model.CountryCode
import com.example.boltfooddemo.data.model.MenuItem
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.data.model.User
import com.example.boltfooddemo.data.network.BoltService
import com.example.boltfooddemo.domain.repository.BoltRepository
import com.example.boltfooddemo.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.Json

class BoltRepositoryImpl(
    private val boltService: BoltService,
    private val userDAO: UserDao,
    private val restaurantDAO: RestaurantDao,
    private val context: Context
): BoltRepository {

    override suspend fun getAllRestaurants(): Resource<List<Restaurant>> {
        val response = boltService.getAllRestaurants()
        return if (response.isSuccessful) {
            Resource.Success(response.body() ?: emptyList())
        } else {
            Resource.Error("Something went wrong")
        }
    }

    override suspend fun getMenu(id: Int): Resource<List<MenuItem>> {
        val response = boltService.getMenu(id)
        return if (response.isSuccessful) {
            Resource.Success(response.body() ?: emptyList())
        } else {
            Resource.Error("Something went wrong")
        }
    }

    override suspend fun insertUser(user: User) {
        userDAO.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        userDAO.updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDAO.deleteUser(user)
    }

    override suspend fun getUserByPhone(phone: String): User? {
        return userDAO.getUserByPhone(phone)
    }

    override suspend fun insertRestaurant(restaurant: Restaurant) {
        restaurantDAO.insertRestaurant(restaurant)
    }

    override fun getAllPastOrders(): Flow<List<Restaurant>> {
        return restaurantDAO.getAllPastOrders()
    }

    override fun loadCountryCodes(): List<CountryCode> {
        val inputStream = context.resources.openRawResource(R.raw.countries)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }

}