package com.ppizil.solutionapp.usecase.network.repository.auth

import com.google.gson.Gson
import com.ppizil.solutionapp.usecase.network.ResponseEntity
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.SharedPreferenceBase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import retrofit2.HttpException
import retrofit2.Response

class AuthModel(
    val authRepo: AuthRepo,
    val gson: Gson
) {

    var userToken: String? = null
    val responseEntity: ResponseEntity by lazy {
        ResponseEntity()
    }

    fun callRegistApi(email: String, nickname: String, pwd: String): Single<ResponseEntity> {

        return authRepo.requestRegistApi(email = email, nickname = nickname, pwd = pwd)
            .flatMap {
                return@flatMap if (it.isSuccessful) {
                    val body = it.body()
                    body?.let {
                        responseEntity.resultCode = it.resultCode
                        responseEntity.message = it.message
                        Single.just(responseEntity)
                    } ?: kotlin.run {
                        Single.just(null)
                    }
                } else {
                    Single.error(HttpException(it))
                }
            }
    }


    fun callLoginApi(email: String, pwd: String): Single<ResponseEntity> {

        return authRepo.requestLoginApi(email, pwd)
            .flatMap {
                return@flatMap if (it.isSuccessful) {
                    val body = it.body()
                    body?.let {
                        val resultCode = body.resultCode
                        when (resultCode) {
                            200 -> {
                                body.resultData.get("user_token").asString?.let {
                                    SharedPreferenceBase.instance.setString("token", it)
                                    responseEntity.resultCode = resultCode
                                    responseEntity.message = body.message
                                    Single.just(responseEntity)
                                } ?: kotlin.run {
                                    Single.just(null)
                                }
                            }
                            else -> {
                                Single.just(null)
                            }
                        }
                    }
                } else {
                    Single.error(HttpException(it))
                }
            }
    }


    fun getUserAccountAPi(token: String): Single<ResponseEntity> {
        return authRepo.getUserAccountApi(token)
            .flatMap {
                return@flatMap if (it.isSuccessful) {
                    val body = it.body()
                    body?.let {
                        when (it.resultCode) {
                            200 -> {
                                responseEntity.resultCode = it.resultCode
                                responseEntity.message = null
                                val resultData = it.resultData
                                Const.convertObjectToString(gson, resultData).apply {
                                    SharedPreferenceBase.instance.setString("USERACCOUNT", this)
                                }
                                Single.just(responseEntity)
                            }
                            else -> {
                                Single.just(null)
                            }
                        }

                    }

                } else {
                    Single.error(HttpException(it))
                }
            }
    }

}