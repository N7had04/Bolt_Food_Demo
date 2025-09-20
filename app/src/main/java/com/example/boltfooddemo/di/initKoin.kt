package com.example.boltfooddemo.di

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(configuration: KoinAppDeclaration? = null) {
    startKoin {
        configuration?.invoke(this)
        modules(networkModule, databaseModule, repositoryModule, useCasesModule, viewModelModules)
    }
}