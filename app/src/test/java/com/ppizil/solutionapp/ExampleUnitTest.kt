package com.ppizil.solutionapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ppizil.solutionapp.viewmodel.auth.AuthViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifyOrder
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    @RelaxedMockK
    lateinit var mockObserver: Observer<Boolean>


    lateinit var viewModel: AuthViewModel
    @Before
    fun init() {
        MockKAnnotations.init(this)

        viewModel = AuthViewModel()
        viewModel.lClickConfirm.observeForever(mockObserver)

    }

    @Test
    fun addition_isCorrect() {

        viewModel.lEmail.value="test"
        viewModel.lPassword.value="sdfssdfsdfsdff"
        viewModel.onClickConfirmBtn()

        viewModel.lEmail.value=null
        viewModel.lPassword.value="sdfsdfsdfsdfsfd"
        viewModel.onClickConfirmBtn()

        viewModel.lEmail.value=""
        viewModel.lPassword.value=""
        viewModel.onClickConfirmBtn()

        verifyOrder {
            mockObserver.onChanged(true)
            mockObserver.onChanged(false)
            mockObserver.onChanged(false)
        }
    }
}