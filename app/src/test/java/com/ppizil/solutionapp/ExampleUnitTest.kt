package com.ppizil.solutionapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ppizil.solutionapp.di.appModule
import com.ppizil.solutionapp.di.networkModule
import com.ppizil.solutionapp.di.userCaseModule
import com.ppizil.solutionapp.di.viewmodelModule
import com.ppizil.solutionapp.usecase.network.repository.auth.AuthModel
import com.ppizil.solutionapp.viewmodel.auth.AuthViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifyOrder
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*

import org.junit.Assert.*
import org.koin.core.KoinComponent
import org.koin.core.context.KoinContextHandler.get
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : KoinComponent {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var registObserver: Observer<Boolean>

    val viewmodel: AuthViewModel by inject()
    @Before
    fun init() {
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }

        startKoin { modules(appModule, viewmodelModule, networkModule, userCaseModule) }
        MockKAnnotations.init(this)

        viewmodel.lResultRegist.observeForever(registObserver)

    }

    @After
    fun after() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun addition_isCorrect() {


        viewmodel.requestUserRegistApi("test44243@naver.com", "55554345", "24!")
        viewmodel.requestUserRegistApi("", "123123132", "fkdl0224!")
        viewmodel.requestUserRegistApi("test435345345@naver.com", "", "fkdl0224!")

        verifyOrder {
            registObserver.onChanged(false)
            registObserver.onChanged(false)
            registObserver.onChanged(false)
        }
    }
}