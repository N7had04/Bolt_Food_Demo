package com.example.boltfooddemo.di

import com.example.boltfooddemo.data.repository.BoltRepositoryImpl
import com.example.boltfooddemo.data.repository.UserPreferencesRepositoryImpl
import com.example.boltfooddemo.domain.repository.BoltRepository
import com.example.boltfooddemo.domain.repository.UserPreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<BoltRepository> { BoltRepositoryImpl(get(), get(), androidContext()) }
    single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(androidContext()) }
}