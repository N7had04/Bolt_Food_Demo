package com.example.boltfooddemo.di

import com.example.boltfooddemo.presentation.viewmodel.AuthViewModel
import com.example.boltfooddemo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { AuthViewModel(get(), get(), get(), get(), get(), get(), get()) }
}