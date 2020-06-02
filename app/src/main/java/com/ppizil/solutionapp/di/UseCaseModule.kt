package com.ppizil.solutionapp.di

import com.ppizil.solutionapp.usecase.network.repository.auth.AuthModel
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthRepo
import org.koin.dsl.module


@JvmField
val userCaseModule = module {

    factory { AuthModel(get()) }

}