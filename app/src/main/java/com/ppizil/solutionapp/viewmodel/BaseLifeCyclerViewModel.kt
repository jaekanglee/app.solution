package com.ppizil.solutionapp.viewmodel

import android.os.Looper
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ppizil.solutionapp.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseLifeCyclerViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val lShowProgress = MutableLiveData<Int>()
    val lMsg = SingleLiveEvent<String>()
    abstract fun onCreate()
    abstract fun onResume()
    abstract fun onPause()
    abstract fun onStop()
    abstract fun onDestroy()
    fun lShowProgress(state: Boolean) {
        if (state) {
            if (isMainThread) {
                lShowProgress.setValue(View.VISIBLE)
            } else {
                lShowProgress.postValue(View.VISIBLE)
            }
        } else {
            if (isMainThread) {
                lShowProgress.setValue(View.GONE)
            } else {
                lShowProgress.postValue(View.GONE)
            }
        }
    }

    fun lShowProgressBackGround(state: Boolean) {
        if (state) {
            lShowProgress.postValue(View.VISIBLE)
        } else {
            lShowProgress.postValue(View.GONE)
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()
}