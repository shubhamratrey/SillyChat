package com.sillylife.realchat.views.module

import com.sillylife.realchat.RealChat
import com.sillylife.realchat.service.network.DBResponses

open class BaseModule {
    val realChat = RealChat.getInstance()
    val getDBResponse = DBResponses.getInstance()

    fun onDestroy() {

    }

}