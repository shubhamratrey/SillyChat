package com.sillylife.realchat.views.module

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.sillylife.realchat.models.User
import com.sillylife.realchat.service.network.DBChildResponse
import com.sillylife.realchat.service.network.DBResponses

class ConversationFragmentModule(val iModuleListener: IModuleListener) : BaseModule() {

    fun loadChatList(limitToLast: Int, lastNodeId: String, hasMore: Boolean?, otherUid: String?) {
        getDBChildResponse.getConversationData(limitToLast, lastNodeId, hasMore!!, otherUid!!, object : DBChildResponse.DbChildCallbacks {
            override fun onCancelled(databaseError: DatabaseError) {
                iModuleListener.onCancelled(databaseError)
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot) {
                iModuleListener.onChildAdded(dataSnapshot, hasMore)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot) {
                iModuleListener.onChildChanged(dataSnapshot)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot) {
                iModuleListener.onChildMoved(dataSnapshot)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                iModuleListener.onChildRemoved(dataSnapshot)
            }
        })
    }

    fun getMe() {
        getDBResponse.getSelfProfile(object : DBResponses.DbCallbacks {
            override fun onSuccess(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue(User::class.java) != null) {
                    iModuleListener.onGetMeSuccess(dataSnapshot.getValue(User::class.java))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                iModuleListener.onGetMeFailure(databaseError)
            }
        })
    }

    interface IModuleListener {
        fun onCancelled(databaseError: DatabaseError)
        fun onChildAdded(dataSnapshot: DataSnapshot, hasMore: Boolean?)
        fun onChildChanged(dataSnapshot: DataSnapshot)
        fun onChildMoved(dataSnapshot: DataSnapshot)
        fun onChildRemoved(dataSnapshot: DataSnapshot)
        fun onGetMeSuccess(user: User?)
        fun onGetMeFailure(databaseError: DatabaseError)
    }
}