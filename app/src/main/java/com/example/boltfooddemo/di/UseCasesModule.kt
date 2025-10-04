package com.example.boltfooddemo.di

import com.example.boltfooddemo.domain.usecase.DeleteUserUseCase
import com.example.boltfooddemo.domain.usecase.GetAllPastOrdersUseCase
import com.example.boltfooddemo.domain.usecase.GetAllRestaurantsUseCase
import com.example.boltfooddemo.domain.usecase.GetAuthTokenUseCase
import com.example.boltfooddemo.domain.usecase.GetLoginStateUseCase
import com.example.boltfooddemo.domain.usecase.GetMenuUseCase
import com.example.boltfooddemo.domain.usecase.GetUserUseCase
import com.example.boltfooddemo.domain.usecase.LoadCountryCodesUseCase
import com.example.boltfooddemo.domain.usecase.SaveLoginStateUseCase
import com.example.boltfooddemo.domain.usecase.SaveMenuItemUseCase
import com.example.boltfooddemo.domain.usecase.SaveUserUseCase
import com.example.boltfooddemo.domain.usecase.UpdateUserUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetAllRestaurantsUseCase(get()) }
    single { GetMenuUseCase(get()) }
    single { SaveUserUseCase(get()) }
    single { UpdateUserUseCase(get()) }
    single { DeleteUserUseCase(get()) }
    single { GetUserUseCase(get()) }
    single { SaveLoginStateUseCase(get()) }
    single { SaveMenuItemUseCase(get()) }
    single { GetAllPastOrdersUseCase(get()) }
    single { LoadCountryCodesUseCase(get()) }
    single { GetLoginStateUseCase(get()) }
    single { GetAuthTokenUseCase(get()) }
}
