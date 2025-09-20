package com.example.boltfooddemo.di

import androidx.room.Room
import com.example.boltfooddemo.data.db.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<UserDatabase> {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    single {
        get<UserDatabase>().userDao
    }
}