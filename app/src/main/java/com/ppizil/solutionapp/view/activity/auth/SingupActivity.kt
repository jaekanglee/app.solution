package com.ppizil.solutionapp.view.activity.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.ppizil.solutionapp.R
import com.ppizil.solutionapp.databinding.ActivitySignupBinding
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.view.activity.BaseActivity
import com.ppizil.solutionapp.viewmodel.auth.SingupViewModel
import org.koin.android.ext.android.inject

class SingupActivity : BaseActivity<ActivitySignupBinding>(
    R.layout.activity_signup,
    adatper = null
) {


    val viewmodel: SingupViewModel by inject ()

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
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
    }

    override fun setObserver() {

        val lClickConfirm = Observer<Boolean> {
            if (it) {
                Const.showToastExeption(this, "Success")
            } else {

            }
        }
        viewmodel.lResultRegist.observe(this, lClickConfirm)

        val lErrorMsg = Observer<String?> {
            it?.let {
                Const.showToastExeption(this, it)
            }
        }
        viewmodel.lMsg.observe(this, lErrorMsg)
    }

    override fun afterInits() {
        viewmodel.onCreate()
    }


    companion object {
        fun startAuthActivity(context: Context, bundle: Bundle?) {
            val intent = Intent(context, SingupActivity::class.java)
            bundle?.let {
                intent.putExtras(it)
            }
            context.startActivity(intent)
        }
    }

}