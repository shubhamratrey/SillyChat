package com.sillylife.realchat.views.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sillylife.realchat.views.fragments.BaseFragment
import com.sillylife.realchat.views.viewmodel.ConversationFragmentViewModel

class FragmentViewModelFactory(private val fragment: BaseFragment) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ConversationFragmentViewModel::class.java) -> return ConversationFragmentViewModel(fragment) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}