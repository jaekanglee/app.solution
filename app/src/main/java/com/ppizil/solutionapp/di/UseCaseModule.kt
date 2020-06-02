package com.ppizil.solutionapp.di

import com.google.gson.Gson
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthModel
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthRepo
import org.koin.dsl.module


@JvmField
val userCaseModule = module {

    single { Gson() }
    factory { AuthModel(get(),get()) }

}