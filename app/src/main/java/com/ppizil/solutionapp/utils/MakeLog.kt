package com.ppizil.solutionapp.utils

import android.util.Log
import com.ppizil.solutionapp.BuildConfig

object MakeLog {
    fun log(tag: String?, name: String?) {
        if (BuildConfig.IS_SHOW_LOG.equals("Y")) Log.e(tag, name)
    }

    fun d(tag: String?, name: String?) {
        if (BuildConfig.IS_SHOW_LOG.equals("Y")) Log.d(tag, name)
    }
}