package com.ppizil.solutionapp.view.activity.intro

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ppizil.solutionapp.R
import com.ppizil.solutionapp.databinding.ActivitySplashBinding
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.MakeLog
import com.ppizil.solutionapp.view.activity.BaseActivity
import com.ppizil.solutionapp.view.activity.auth.SingupActivity
import com.ppizil.solutionapp.viewmodel.splash.SplashVm

class SplashActivity : BaseActivity<ActivitySplashBinding>(
    R.layout.activity_splash,
    null
) {

    private val viewmodel :SplashVm by lazy {
        ViewModelProvider(this)[SplashVm::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtrasData()
        setInitData()
        bindViewModel()
        setObserver()
        afterInits()
    }

    override fun setInitData() {
        MakeLog.log("sdfsdfsdf","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    override fun getExtrasData() {
    }

    override fun bindViewModel() {

    }

    override fun setObserver() {
        viewmodel.lGoAuthActivity.observe(this, Observer {
            when(it){
                true ->{
                    SingupActivity.startAuthActivity(this,null)
                }
                else ->{
                    Const.showToastExeption(this,"Error")
                }
            }
        })
    }

    override fun afterInits() {
        viewmodel.onCreate()
    }
}