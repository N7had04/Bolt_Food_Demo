package com.example.boltfooddemo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boltfooddemo.data.model.FavRestaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface FavRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavRestaurant(restaurant: FavRestaurant)

    @Delete
    suspend fun deleteFavRestaurant(restaurant: FavRestaurant)

    @Query("SELECT * FROM favourite_restaurants")
    fun getAllFavRestaurants(): Flow<List<FavRestaurant>>

}