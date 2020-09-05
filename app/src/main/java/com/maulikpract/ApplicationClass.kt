package com.maulikpract

import android.app.Application
import com.maulikpract.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule, stateModule, viewModelModule, appModule))
        }
    }
}