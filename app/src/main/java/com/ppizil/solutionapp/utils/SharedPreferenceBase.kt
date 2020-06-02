package com.ppizil.solutionapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceBase {


    var pref: SharedPreferences? = null
        private set
    var editor: SharedPreferences.Editor? = null
        private set
    var context: Context? =null
    set(value) {
        field = value
        pref = field?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        editor = pref?.edit()
    }

    fun clearAllData() {
        editor?.clear()
        editor?.commit()
    }



    fun setString(key: String?, value: String?) {
        editor?.putString(key, value)
        editor?.commit()
    }

    fun setBoolean(key: String?, value: Boolean?) {
        editor?.putBoolean(key, value!!)
        editor?.commit()
    }

    fun setInt(key: String?, value: Int) {
        editor?.putInt(key, value)
        editor?.commit()
    }

    fun getString(key: String?, value: String?): String? {
        pref = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref?.getString(key, value)
    }

    fun getInt(key: String?, value: Int): Int {
        pref = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref?.getInt(key, value)?:value
    }

    fun getBoolean(key: String?, value: Boolean): Boolean {
        pref = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref?.getBoolean(key, value)?:value
    }

    fun setLong(key: String?, value: Long) {
        editor!!.putLong(key, value)
        editor!!.commit()
    }

    fun getLong(key: String?, value: Long): Long {
        pref = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref?.getLong(key, value)?:value
    }

    companion object {
        val instance = SharedPreferenceBase()
    }
}