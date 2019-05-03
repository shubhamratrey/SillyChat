package com.sillylife.realchat.views.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sillylife.realchat.views.activities.BaseActivity
import com.sillylife.realchat.views.viewmodel.MainActivityViewModel

class ActivityViewModelFactory(private val activity: BaseActivity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> return MainActivityViewModel(activity) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}