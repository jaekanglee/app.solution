package com.ppizil.solutionapp.di

import com.ppizil.solutionapp.viewmodel.auth.AuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField

val viewmodelModule = module{


    viewModel {
        AuthViewModel(get())
    }
}