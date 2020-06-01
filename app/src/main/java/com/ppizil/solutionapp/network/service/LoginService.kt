package com.ppizil.solutionapp.network.service

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {


    @FormUrlEncoded
    @POST("user/regist")
    fun callLoginApi(
        @Field("email") email: String?,
        nickname: String?,
        password: String?
    )


}