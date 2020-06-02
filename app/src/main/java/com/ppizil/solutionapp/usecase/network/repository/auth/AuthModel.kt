package com.ppizil.solutionapp.usecase.network.repository.auth

import com.ppizil.solutionapp.usecase.network.ResponseEntity
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.SharedPreferenceBase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import retrofit2.HttpException

class AuthModel(val authRepo: AuthRepo) {

    var userToken: String? = null
    val responseEntity : ResponseEntity by lazy {
        ResponseEntity()
    }
    fun callRegistApi(email: String, nickname: String, pwd: String): Single<ResponseEntity> {

        return authRepo.requestRegistApi(email = email, nickname = nickname, pwd = pwd)
            .flatMap {
                return@flatMap if (it.isSuccessful) {
                    val body = it.body()
                    body?.let {
                        responseEntity.resultCode= it.resultCode
                        responseEntity.message= it.message
                        Single.just(responseEntity)
                    } ?: kotlin.run {
                        Single.just(null)
                    }
                } else {
                    Single.error(HttpException(it))
                }
            }


    }

}