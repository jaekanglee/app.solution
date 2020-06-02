package com.ppizil.solutionapp.viewmodel.auth

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ppizil.solutionapp.di.viewmodelModule
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthModel
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.viewmodel.BaseLifeCyclerViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Response

class AuthViewModel(private val authModel: AuthModel) : BaseLifeCyclerViewModel() {

    val lEmail = MutableLiveData<String>()
    val lPassword = MutableLiveData<String>()
    val lNickname = MutableLiveData<String>()
    val lClickConfirm = MutableLiveData<Boolean>()


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
            requestUserRegistApi(email!!, nickname!!, pwd!!)
        } else {
            lMsg.value = "Not Valid Form, Check Your Infomation"
            lClickConfirm.value = false
        }
    }

    fun checkVaildForm(email: String?, nickname: String?, pwd: String?): Boolean {
        if (Const.isNotNullString(email, nickname, pwd)) {
            return pwd?.length ?: 0 > 7
        } else {
            return false
        }
    }

    fun requestUserRegistApi(email: String, nickname: String, pwd: String) {
        compositeDisposable.add(
            authModel.callRegistApi(email, nickname, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        when(it.resultCode){
                            200->{
                                lClickConfirm.value=(false)
                            }
                            else ->{
                                lClickConfirm.value=(true)
                            }
                        }
                        lMsg.postValue(it.message)


                    }, {
                        lMsg.value=(it.localizedMessage)
                    }
                )
        )
    }


}