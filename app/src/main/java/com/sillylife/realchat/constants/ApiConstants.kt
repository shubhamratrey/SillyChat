package com.sillylife.realchat.constants

import com.sillylife.realchat.constants.DbConstants.Companion.API_V1
import com.sillylife.realchat.constants.DbConstants.Companion.CHAT_LIST
import com.sillylife.realchat.constants.DbConstants.Companion.CONVERSATION_LIST
import com.sillylife.realchat.constants.DbConstants.Companion.NOTIFICATION_LIST
import com.sillylife.realchat.constants.DbConstants.Companion.PROFILE

class ApiConstants {
    companion object {
        const val USERS_PROFILE = "$API_V1/$PROFILE"
        const val USERS_CHAT_LIST = "$API_V1/$CHAT_LIST"
        const val USERS_CONVERSATION_LIST = "$API_V1/$CONVERSATION_LIST"
        const val NOTIFICATIONS_LIST = "$API_V1/$NOTIFICATION_LIST"
    }
}