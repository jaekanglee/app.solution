package com.ppizil.solutionapp.usecase.network

import com.ppizil.solutionapp.usecase.network.service.AuthService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper() {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl("http://www.mogacko.kro.kr/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bodyLogger :HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
    val headerLogger :HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        }
    }
    val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            this.addInterceptor(headerLogger)
            this.addInterceptor(bodyLogger)
        }
    }

    fun createLoginServer():AuthService{
        return retrofit.create(AuthService::class.java)
    }

}