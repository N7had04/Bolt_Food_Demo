package com.example.boltfooddemo

import android.app.Application
import com.example.boltfooddemo.di.initKoin
import org.koin.android.ext.koin.androidContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
        }
    }
}