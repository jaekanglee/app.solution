package com.ppizil.solutionapp.di

import com.ppizil.solutionapp.usecase.network.RetrofitHelper
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthRepo
import org.koin.dsl.module

@JvmField
val networkModule = module {


    single {
        RetrofitHelper()
    }

    factory {
        AuthRepo((get() as RetrofitHelper).createLoginServer())
    }

}
