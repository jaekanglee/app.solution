package com.ppizil.solutionapp

import android.app.Application
import com.ppizil.solutionapp.di.appModule
import com.ppizil.solutionapp.di.networkModule
import com.ppizil.solutionapp.di.userCaseModule
import com.ppizil.solutionapp.di.viewmodelModule
import com.ppizil.solutionapp.utils.SharedPreferenceBase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication  :Application(){


    override fun onCreate() {
        super.onCreate()
        SharedPreferenceBase.instance.context= this

        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(appModule,viewmodelModule , userCaseModule, networkModule)
        }
    }
}