package com.ppizil.solutionapp.usecase.network.repository.auth

import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.SharedPreferenceBase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class AuthModel(val authRepo: AuthRepo) {

    var userToken: String? = null

    fun callRegistApi(email: String, nickname: String, pwd: String): Single<String?> {

        return authRepo.requestRegistApi(email = email, nickname = nickname, pwd = pwd)
            .flatMap {
                return@flatMap if (it.isSuccessful) {
                    val body = it.body()
                    body?.let {
                        Single.just(it.message)
                    } ?: kotlin.run {
                        Single.just(null)
                    }
                } else {
                    Single.error(HttpException(it))
                }
            }


    }

}