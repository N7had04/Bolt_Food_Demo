package com.example.boltfooddemo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boltfooddemo.data.model.MenuItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: MenuItem)

    @Query("SELECT * FROM menu_item")
    fun getAllPastOrders(): Flow<List<MenuItem>>
}