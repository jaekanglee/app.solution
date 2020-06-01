package com.ppizil.solutionapp.di

import com.google.gson.Gson
import com.ppizil.solutionapp.network.RetrofitHelper
import com.ppizil.solutionapp.network.repository.LoginRepo
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@JvmField
val networkModule = module {


    single {
        RetrofitHelper()
    }

    factory {
        LoginRepo((get() as RetrofitHelper).createLoginServer())
    }

}
