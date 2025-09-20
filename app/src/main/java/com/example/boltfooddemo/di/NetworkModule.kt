package com.example.boltfooddemo.di

import com.example.boltfooddemo.BuildConfig
import com.example.boltfooddemo.data.network.BoltService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.jvm.java

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = false
        }
    }

    single {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(get<Json>().asConverterFactory(contentType))
            .build()
    }

    single<BoltService> {
        get<Retrofit>().create(BoltService::class.java)
    }
}