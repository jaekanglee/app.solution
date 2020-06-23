package com.ppizil.solutionapp.utils

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import java.util.regex.Pattern

object Const {


    fun showToastExeption(context: Context?, msg: String?) {
        msg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        }
    }

    fun isNotNullString(vararg  params:String?):Boolean{
        params.forEach {
            if(it.isNullOrEmpty()){
                return false
            }
        }
        return true;
    }

    fun isNotNullEmptyArrayList( list : List<Any>):Boolean{
        return !list.isNullOrEmpty()
    }

    fun checkEmailForm(email:String?):Boolean{
        email?.let {
           return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }?: kotlin.run {
            return false
        }
    }

    fun checkVaildForm(email: String?, nickname: String?, pwd: String?): Boolean {
        return if (Const.isNotNullString(email, nickname, pwd)) {
            pwd?.length ?: 0 > 7
        } else {
            false
        }
    }

    fun convertObjectToString(gson:Gson,values:Any):String{
        return gson.toJson(values)
    }
}