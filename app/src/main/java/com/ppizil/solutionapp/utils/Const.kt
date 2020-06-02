package com.ppizil.solutionapp.utils

import android.content.Context
import android.widget.Toast
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

    val emailRegax = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\\\w+\\\\.)+\\\\w+\$";
    fun checkEmailForm(email:String):Boolean{
        val pattern = Pattern.compile(emailRegax)
        var result =  pattern.matcher(email)
        return result.matches()
    }

    fun checkVaildForm(email: String?, nickname: String?, pwd: String?): Boolean {
        return if (Const.isNotNullString(email, nickname, pwd)) {
            pwd?.length ?: 0 > 7
        } else {
            false
        }
    }
}