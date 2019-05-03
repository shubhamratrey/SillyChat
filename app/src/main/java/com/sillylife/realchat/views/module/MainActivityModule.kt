package com.sillylife.realchat.views.module

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.sillylife.realchat.service.network.DBResponses

class MainActivityModule(val iModuleListener: IModuleListener) : BaseModule() {

    fun getMe() {
        getDBResponse.getSelfProfile(object : DBResponses.DbCallbacks {
            override fun onSuccess(dataSnapshot: DataSnapshot) {
                iModuleListener.onGetMeResponseSuccess(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                iModuleListener.onGetMeResponseFailure(databaseError)
            }

        })
    }

    interface IModuleListener {
        fun onGetMeResponseSuccess(dataSnapshot: DataSnapshot)
        fun onGetMeResponseFailure(databaseError: DatabaseError)
    }

}