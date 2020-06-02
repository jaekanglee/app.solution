package com.ppizil.solutionapp.viewmodel.auth

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ppizil.solutionapp.di.viewmodelModule
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthModel
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.Const.checkEmailForm
import com.ppizil.solutionapp.utils.Const.checkVaildForm
import com.ppizil.solutionapp.viewmodel.BaseLifeCyclerViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Response
import java.util.regex.Pattern

class SingupViewModel(private val authModel: AuthModel) : BaseLifeCyclerViewModel() {

    val lEmail = MutableLiveData<String>()
    val lPassword = MutableLiveData<String>()
    val lNickname = MutableLiveData<String>()
    val lResultRegist = MutableLiveData<Boolean>()



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


    fun onClickConfirmBtn() {
        val email = lEmail.value
        val nickname = lNickname.value
        val pwd = lPassword.value
        if (checkVaildForm(email, nickname, pwd)) {
            if(checkEmailForm(email!!)){
                requestUserRegistApi(email, nickname!!, pwd!!)
            }
            else{
                lMsg.value = "Not Valid Email"
                lResultRegist.value = false
            }
        } else {
            lMsg.value = "Not Valid Form, Check Your Infomation"
            lResultRegist.value = false
        }
    }


    fun requestUserRegistApi(email: String, nickname: String, pwd: String) {
        compositeDisposable.add(
            authModel.callRegistApi(email, nickname, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onSuccess = {
                        when(it.resultCode){
                            200->{
                                lResultRegist.value=(true)
                            }
                            else ->{
                                lResultRegist.value=(false)
                            }
                        }
                        lMsg.postValue(it.message)
                    },
                    onError = {
                        lResultRegist.value=(false)
                        lMsg.value=(it.localizedMessage)
                    }
                )
        )
    }


}