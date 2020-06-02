package com.ppizil.solutionapp.viewmodel.auth

import androidx.lifecycle.MutableLiveData
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthModel
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.SingleLiveEvent
import com.ppizil.solutionapp.viewmodel.BaseLifeCyclerViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    val authModel: AuthModel
) :BaseLifeCyclerViewModel(){

    val lEmail =MutableLiveData<String>()
    val lPwd  = MutableLiveData<String>()
    val lResultLogin = SingleLiveEvent<Boolean>()


    override fun onCreate() {

    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }



    fun onClickConfirmBtn(){
        val email = lEmail.value?:""
        val pwd = lPwd.value?:""
        if(Const.isNotNullString(email,pwd)){
            if(Const.checkEmailForm(email)){
                requestLoginApi(email,pwd)
            }
            else{
                lMsg.value="Not Valid Email Form"
            }
        }
        else{
            lMsg.value="Must be not null or empty"
        }
    }


    fun requestLoginApi(email:String,pwd:String){
        compositeDisposable.add(
            authModel.callLoginApi(email,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onSuccess = {
                        when(it.resultCode){
                            200->{
                                lResultLogin.value=true
                            }
                            else->{
                                lResultLogin.value=false
                            }
                        }
                        lMsg.value= it.message
                    },
                    onError = {
                        lMsg.value= it.localizedMessage
                        lResultLogin.value=false
                    }
                )
        )
    }



}