package com.ppizil.solutionapp.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ppizil.solutionapp.R
import com.ppizil.solutionapp.databinding.ActivityMainBinding
import com.ppizil.solutionapp.view.activity.BaseActivity
import com.ppizil.solutionapp.view.activity.auth.LoginActivity

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main,
    null
) {


    override fun setInitData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExtrasData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setObserver() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun afterInits() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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