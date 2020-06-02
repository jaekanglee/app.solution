package com.ppizil.solutionapp.di

import com.ppizil.solutionapp.viewmodel.auth.LoginViewModel
import com.ppizil.solutionapp.viewmodel.auth.SingupViewModel
import com.ppizil.solutionapp.viewmodel.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField

val viewmodelModule = module{


    viewModel {
        SingupViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        MainViewModel(get())
    }
}