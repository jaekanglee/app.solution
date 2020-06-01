package com.ppizil.solutionapp.viewmodel.splash

import com.ppizil.solutionapp.utils.MakeLog
import com.ppizil.solutionapp.utils.SingleLiveEvent
import com.ppizil.solutionapp.viewmodel.BaseLifeCyclerViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class SplashVm : BaseLifeCyclerViewModel(), SplashImpl {

    var lGoAuthActivity = SingleLiveEvent<Boolean>()
    override fun onCreate() {
        delayTime()
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delayTime() {
        compositeDisposable.add(
            Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onComplete = {
                        getAuthActivity()
                    },
                    onError = {
                        MakeLog.d("delayTimer", it.localizedMessage)
                    }
                )
        )
    }

    override fun getAuthActivity() {
        lGoAuthActivity.value=true
    }

    override fun setProgressMsg() {
    }

}