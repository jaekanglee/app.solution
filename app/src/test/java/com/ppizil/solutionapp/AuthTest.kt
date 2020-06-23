package com.ppizil.solutionapp

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ppizil.solutionapp.di.appModule
import com.ppizil.solutionapp.di.networkModule
import com.ppizil.solutionapp.di.userCaseModule
import com.ppizil.solutionapp.di.viewmodelModule
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.SharedPreferenceBase
import com.ppizil.solutionapp.viewmodel.auth.LoginViewModel
import com.ppizil.solutionapp.viewmodel.auth.SingupViewModel
import com.ppizil.solutionapp.viewmodel.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifyOrder
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*

import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AuthTest : KoinComponent{

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var registObserver: Observer<Boolean>

    @RelaxedMockK
    lateinit var loginObserver: Observer<Boolean?>

    @RelaxedMockK
    lateinit var userAccountObserver: Observer<Boolean?>

    val viewmodel: SingupViewModel by inject()
    val loginViewModel: LoginViewModel by inject()
    val mainViewModel :MainViewModel by inject()


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
        loginViewModel.lResultLogin.observeForever(loginObserver)
        mainViewModel.lResultUserAccount.observeForever(userAccountObserver)
    }

    @After
    fun after() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()

        stopKoin()
    }
/*

    @Test
    fun `이메일 형식에 맞고, 입력 폼에 맞고, 가입이 정상적으로 리턴 됨 `() {


        viewmodel.requestUserRegistApi("test44243naver.com", "55554345", "24!")
        viewmodel.requestUserRegistApi("", "123123132", "fkdl0224!")
        viewmodel.requestUserRegistApi("테스트안함?@naver.com", "이재강", "fkdl0224!")

        verifyOrder {
            registObserver.onChanged(false)
            registObserver.onChanged(false)
            registObserver.onChanged(false)
        }
    }

    @Test
    fun `이메일 형식에 맞고, 패스워드 길이가 통과되고, 정상적으로 로그인 하며, 토큰을 받아와야함 `() {

        loginViewModel.requestLoginApi("admidn@naver.com", "fkdl0224!")
        loginViewModel.requestLoginApi("naver.com", "fkdl0224!")
        loginViewModel.requestLoginApi("", "fkdl0224!")
        loginViewModel.requestLoginApi("admin@naver.com", "fkdl")


        verifyOrder {
            loginObserver.onChanged(false)
            loginObserver.onChanged(false)
            loginObserver.onChanged(false)
            loginObserver.onChanged(false)
        }
    }

    @Test
    fun `유저 어카운트 가져오기`(){
        mainViewModel.getUserAccountApi(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImFkbWluQG5hdmVyLmNvbSIsInVpZCI6MTUsIm5pY2tuYW1lIjoi7J207J6s6rCVIiwiaWF0IjoxNTkxMTA0ODk0LCJleHAiOjE1OTExOTEyOTR9.jRP1G7MPZkE8bheUGwFrvCIF8dsT39Gf6zoyrt1sOS0"
        )

        verifyOrder {
            userAccountObserver.onChanged(false)
        }
    }

*/


    @Test
    fun `가입시 유효성 체크 `(){
        val isNotNull = Const.isNotNullString("ksdy200@gmail.com","fkdl0224!","sdfsdfsdfsdf")
        assert(isNotNull)
    }

    @Test
    fun `이메일 유효성 체크 `(){
        var  isValid:Boolean = Const.checkEmailForm("ksdy200gmail.com")
        assert(isValid)
    }


}