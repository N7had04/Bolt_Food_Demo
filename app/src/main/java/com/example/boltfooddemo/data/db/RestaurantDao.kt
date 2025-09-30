package com.example.boltfooddemo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boltfooddemo.data.model.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant")
    fun getAllPastOrders(): Flow<List<Restaurant>>
}