package com.sillylife.realchat.views.viewmodel

import com.sillylife.realchat.views.fragments.BaseFragment
import com.sillylife.realchat.views.module.AppLanguageFragmentModule
import com.sillylife.realchat.views.module.BaseModule

class AppLanguageFragmentViewModel(fragment: BaseFragment) : BaseViewModel(),
    AppLanguageFragmentModule.IModuleListener {


    val module = AppLanguageFragmentModule(this)
    val viewListener = fragment as AppLanguageFragmentModule.IModuleListener
    override fun setViewModel(): BaseModule {
        return module
    }

    fun sendAppLanguageSlug(slug: String) {
        module.sendAppLanguageSlug(slug)
    }

    fun getAppLanguages() {
        module.getAppLanguages()
    }

}
