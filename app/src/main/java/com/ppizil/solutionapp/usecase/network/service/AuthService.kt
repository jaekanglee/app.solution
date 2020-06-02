package com.ppizil.solutionapp.usecase.network.service

import com.ppizil.solutionapp.usecase.network.ResultDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {


    @FormUrlEncoded
    @POST("user/regist")
    fun callRegistApi(
        @Field("email") email: String?,
        @Field("nickname") nickname: String?,
        @Field("password") password: String?
    ): Single<Response<ResultDto>>


    @FormUrlEncoded
    @POST("user/login")
    fun callLoginApi(
        @Field("email") email :String?,
        @Field("password") pwd :String?
    ):Single<Response<ResultDto>>


    @FormUrlEncoded
    @POST("user/account")
    fun callAccountApi(
        @Field("header") token:String
    )
    :Single<Response<ResultDto>>
}