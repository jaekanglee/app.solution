package com.ppizil.solutionapp

import android.app.Application
import com.ppizil.solutionapp.di.appModule
import com.ppizil.solutionapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication  :Application(){


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(appModule, networkModule)
        }
    }
}