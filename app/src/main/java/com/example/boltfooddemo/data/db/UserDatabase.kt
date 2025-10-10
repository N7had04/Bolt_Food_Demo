package com.example.boltfooddemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boltfooddemo.data.model.FavRestaurant
import com.example.boltfooddemo.data.model.Restaurant
import com.example.boltfooddemo.data.model.User

@Database(entities = [User::class, Restaurant::class, FavRestaurant::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val restaurantDao: RestaurantDao
    abstract val favRestaurantDao: FavRestaurantDao
}