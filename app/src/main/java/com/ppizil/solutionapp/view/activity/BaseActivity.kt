package com.ppizil.solutionapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ppizil.solutionapp.utils.Const
import com.ppizil.solutionapp.utils.SingleLiveEvent

abstract class BaseActivity<T : ViewDataBinding>(

    private val layoutId: Int,
    private val adatper: RecyclerView.Adapter<RecyclerView.ViewHolder>?

) : AppCompatActivity() {


    lateinit var binding: T
    var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    val lErrorMsg = SingleLiveEvent<String?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)

        registDefaultObserver()
    }


    fun registDefaultObserver() {

        val lErrorMsgObserver: Observer<String?> = Observer {
            it?.let {
                Const.showToastExeption(this,it)
            }
        }
        lErrorMsg.observe(this, lErrorMsgObserver)
    }

    fun recyclerViewAdapterNotify(pos: Int) {
        if (pos > 0) {
            adapter?.notifyItemChanged(pos)
        } else {
            adatper?.notifyDataSetChanged()
        }
    }


    abstract fun setInitData()
    abstract fun getExtrasData()
    abstract fun bindViewModel()
    abstract fun setObserver()
    abstract fun afterInits()
}