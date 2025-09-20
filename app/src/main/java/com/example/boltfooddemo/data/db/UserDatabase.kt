package com.example.boltfooddemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boltfooddemo.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}