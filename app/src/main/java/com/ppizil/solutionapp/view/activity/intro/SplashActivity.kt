package com.ppizil.solutionapp.view.activity.intro

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ppizil.solutionapp.R
import com.ppizil.solutionapp.databinding.SplashActivityBinding
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.MakeLog
import com.ppizil.solutionapp.view.activity.BaseActivity
import com.ppizil.solutionapp.view.activity.auth.AuthActivity
import com.ppizil.solutionapp.viewmodel.splash.SplashVm

class SplashActivity : BaseActivity<SplashActivityBinding>(
    R.layout.splash_activity,
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
                    AuthActivity.startAuthActivity(this,null)
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