package com.ppizil.solutionapp.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ppizil.solutionapp.R
import com.ppizil.solutionapp.databinding.ActivityMainBinding
import com.ppizil.solutionapp.view.activity.BaseActivity
import com.ppizil.solutionapp.view.activity.auth.LoginActivity
import com.ppizil.solutionapp.viewmodel.main.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
    null
) {


    val mainViewModel :MainViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtrasData()
        setInitData()
        bindViewModel()
        setObserver()
        afterInits()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.onResume()
    }

    override fun setInitData() {
    }

    override fun getExtrasData() {
    }

    override fun bindViewModel() {
    }

    override fun setObserver() {
    }

    override fun afterInits() {
    }

    companion object {
        fun startAuthActivity(context: Context, bundle: Bundle?) {
            val intent = Intent(context, MainActivity::class.java)
            bundle?.let {
                intent.putExtras(it)
            }
            context.startActivity(intent)
        }
    }
}