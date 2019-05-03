package com.sillylife.realchat.views.viewmodel

import android.os.Handler
import androidx.lifecycle.ViewModel
import com.sillylife.realchat.views.module.BaseModule

open abstract class BaseViewModel : ViewModel() {

    private var baseModule: BaseModule? = null

    abstract fun setViewModel(): BaseModule

    init {
        init()
    }

    private fun init() {
        Handler().postDelayed({
            baseModule = setViewModel()

        }, 200)
    }

    fun onDestroy() {
        baseModule?.onDestroy()
    }
}