package com.ppizil.solutionapp.utils

import android.content.Context
import android.widget.Toast

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
}