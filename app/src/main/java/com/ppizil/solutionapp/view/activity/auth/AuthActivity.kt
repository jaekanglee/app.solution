package com.ppizil.solutionapp.view.activity.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ppizil.solutionapp.R
import com.ppizil.solutionapp.databinding.AuthActivityBinding
import com.ppizil.solutionapp.view.activity.BaseActivity

class AuthActivity : BaseActivity<AuthActivityBinding>(
    R.layout.auth_activity,
    adatper = null
    ) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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


    companion object{
        fun startAuthActivity(context:Context,bundle: Bundle?){
            val intent = Intent(context,AuthActivity::class.java)
            bundle?.let {
                intent.putExtras(it)
            }
            context.startActivity(intent)
        }
    }

}