package com.sillylife.realchat.service.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sillylife.realchat.constants.ApiConstants
import com.sillylife.realchat.models.*
import java.util.*

class UpdateDatabase {

    companion object {
        @JvmStatic
        fun getInstance(): UpdateDatabase {
            var instance: UpdateDatabase? = null
            if (instance == null) {
                instance = UpdateDatabase()
            }
            return instance
        }
    }

    private val uid = FirebaseAuth.getInstance().currentUser?.uid!!
    private val time = Calendar.getInstance().time.time.toString()


    fun updateUserProfile(userProfile: User) {
        val myRef = FirebaseDatabase.getInstance().reference
        myRef.child(ApiConstants.USERS_PROFILE).child(uid).setValue(userProfile)
    }

    fun sendMessages(otherUserProfile: User, selfUserProfile: User, convMessage: String, messageType: String) {
        val myRef = FirebaseDatabase.getInstance().reference
        val selfKey = getConversationKey(uid, otherUserProfile.userId!!)
        val otherKey = getConversationKey(otherUserProfile.userId!!, uid)

        myRef.child(ApiConstants.USERS_CONVERSATION_LIST).child(uid).child(otherUserProfile.userId!!).child(selfKey)
                .setValue(Conversation(selfKey, convMessage, time, true,
                        ConvUser(selfUserProfile.userImage, selfUserProfile.userName, selfUserProfile.userId)))

        myRef.child(ApiConstants.USERS_CONVERSATION_LIST).child(otherUserProfile.userId!!).child(uid).child(otherKey)
                .setValue(Conversation(otherKey, convMessage, time, false,
                        ConvUser(selfUserProfile.userImage, selfUserProfile.userName, selfUserProfile.userId)))

        myRef.child(ApiConstants.USERS_CHAT_LIST).child(otherUserProfile.userId!!).child(uid)
                .setValue(ChatItem(time, selfUserProfile.userId, convMessage, messageType,
                        ConvUser(selfUserProfile.userImage, selfUserProfile.userName, selfUserProfile.userId)))

        myRef.child(ApiConstants.USERS_CHAT_LIST).child(uid).child(otherUserProfile.userId!!)
                .setValue(ChatItem(time, otherUserProfile.userId, convMessage, messageType,
                        ConvUser(otherUserProfile.userImage, otherUserProfile.userName, otherUserProfile.userId)))

        updateNotification(Notifications(selfUserProfile.userImage, selfUserProfile.userName, selfUserProfile.userId, convMessage), otherUserProfile.userId!!)
    }

    fun updateNotification(notifications: Notifications, mChatUserId: String) {
        val myRef = FirebaseDatabase.getInstance().reference
        myRef.child(ApiConstants.NOTIFICATIONS_LIST).child(mChatUserId).child(getNotificationKey(uid)).setValue(notifications)
    }

    fun getConversationKey(firstId: String, secondId: String): String {
        val myRef = FirebaseDatabase.getInstance().reference
        return myRef.child(ApiConstants.USERS_CONVERSATION_LIST).child(firstId).child(secondId).push().key!!
    }

    fun getNotificationKey(mChatUserId: String): String {
        val myRef = FirebaseDatabase.getInstance().reference
        return myRef.child(ApiConstants.NOTIFICATIONS_LIST).child(mChatUserId).push().key!!
    }
}
