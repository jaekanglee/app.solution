package com.ppizil.solutionapp.view.activity.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ppizil.solutionapp.R
import com.ppizil.solutionapp.databinding.ActivityLoginBinding
import com.ppizil.solutionapp.view.activity.BaseActivity
import com.ppizil.solutionapp.view.main.MainActivity
import com.ppizil.solutionapp.viewmodel.auth.LoginViewModel
import org.koin.android.ext.android.inject

class LoginActivity :BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login,
    null
){

    val viewmodel :LoginViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtrasData()
        setInitData()
        bindViewModel()
        setObserver()
        afterInits()
    }

    override fun setInitData() {
    }

    override fun getExtrasData() {
    }

    override fun bindViewModel() {
        binding.viewmodel=viewmodel
        binding.lifecycleOwner=this
    }

    override fun setObserver() {

        val lReusltLoginObserver = Observer<Boolean?>{
         it.let {
             when(it){
                 true ->{
                     MainActivity.startAuthActivity(this,null)
                 }
                 else->{

                 }
             }
         }
        }
        viewmodel.lResultLogin.observe(this,lReusltLoginObserver)


        val lClickSignupBtn = Observer<Boolean?> {
            it?.let {
                if(it){
                    SingupActivity.startAuthActivity(this,null)
                }
            }

        }
        viewmodel.lClickSignupBtn.observe(this,lClickSignupBtn)
    }

    override fun afterInits() {
    }

    companion object {
        fun startAuthActivity(context: Context, bundle: Bundle?) {
            val intent = Intent(context, LoginActivity::class.java)
            bundle?.let {
                intent.putExtras(it)
            }
            context.startActivity(intent)
        }
    }
}