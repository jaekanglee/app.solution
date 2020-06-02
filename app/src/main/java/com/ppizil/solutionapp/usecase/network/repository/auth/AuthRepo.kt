package com.ppizil.solutionapp.usecase.network.repository.auth

import com.ppizil.solutionapp.usecase.network.ResultDto
import com.ppizil.solutionapp.usecase.network.service.AuthService
import io.reactivex.Single
import retrofit2.Response


class AuthRepo (val authServer : AuthService){



    fun requestRegistApi(email:String,nickname:String,pwd:String):Single<Response<ResultDto>>{
       return  authServer.callRegistApi(email ,nickname ,pwd)
    }


    fun requestLoginApi(email:String,passowrd:String):Single<Response<ResultDto>>{
        return authServer.callLoginApi(email,passowrd)
    }

    fun getUserAccountApi(token:String):Single<Response<ResultDto>>{
        return authServer.callAccountApi(token)
    }
}