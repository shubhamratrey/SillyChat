package com.sillylife.realchat.views.viewmodel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.sillylife.realchat.views.activities.BaseActivity
import com.sillylife.realchat.views.module.BaseModule
import com.sillylife.realchat.views.module.MainActivityModule

class MainActivityViewModel(activity: BaseActivity) : BaseViewModel(), MainActivityModule.IModuleListener {
    override fun onGetMeResponseSuccess(dataSnapshot: DataSnapshot) {
        viewListener.onGetMeResponseSuccess(dataSnapshot)
    }

    override fun onGetMeResponseFailure(databaseError: DatabaseError) {
        viewListener.onGetMeResponseFailure(databaseError)
    }

    val module = MainActivityModule(this)
    val viewListener = activity as MainActivityModule.IModuleListener
    override fun setViewModel(): BaseModule {
        return module
    }

    fun getMe() {
        module.getMe()
    }
}
