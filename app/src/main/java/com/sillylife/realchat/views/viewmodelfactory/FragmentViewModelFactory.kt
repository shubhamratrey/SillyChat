package com.sillylife.realchat.views.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sillylife.realchat.views.fragments.BaseFragment
import com.sillylife.realchat.views.viewmodel.AppLanguageFragmentViewModel

class FragmentViewModelFactory(private val fragment: BaseFragment) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            //modelClass.isAssignableFrom(AppLanguageFragmentViewModel::class.java) -> return AppLanguageFragmentViewModel(fragment) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}