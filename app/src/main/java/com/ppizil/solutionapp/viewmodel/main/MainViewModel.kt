package com.ppizil.solutionapp.viewmodel.main

import com.ppizil.solutionapp.usecase.network.repository.auth.AuthModel
import com.ppizil.solutionapp.utils.SharedPreferenceBase
import com.ppizil.solutionapp.utils.SingleLiveEvent
import com.ppizil.solutionapp.viewmodel.BaseLifeCyclerViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    val authModel: AuthModel
):BaseLifeCyclerViewModel(){


    val lResultUserAccount = SingleLiveEvent<Boolean>()

    override fun onCreate() {
    }

    override fun onResume() {
        getUserAccountApi("")
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }


    fun getUserAccountApi(token:String){
        SharedPreferenceBase.instance.getString("user_token",null)?.let {
            compositeDisposable.add(
                authModel.getUserAccountAPi(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy (
                        onSuccess = {
                            when(it.resultCode){
                                200->{
                                    lResultUserAccount.value=true
                                }
                                else->{
                                    lResultUserAccount.value=false
                                }
                            }
                            lMsg.value= it.message
                        },
                        onError = {
                            lMsg.value= it.localizedMessage
                            lResultUserAccount.value=false
                        }
                    )
            )
        }?: kotlin.run {
            lResultUserAccount.value=false
        }

    }

}