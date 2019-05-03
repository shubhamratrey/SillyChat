package com.sillylife.realchat.service.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sillylife.realchat.constants.ApiConstants
import com.sillylife.realchat.constants.DbConstants

class DBChildResponse {

    companion object {
        @JvmStatic
        fun getInstance(): DBChildResponse {
            var instance: DBChildResponse? = null
            if (instance == null) {
                instance = DBChildResponse()
            }
            return instance
        }
    }

    interface DbChildCallbacks {
        fun onCancelled(databaseError: DatabaseError)
        fun onChildAdded(dataSnapshot: DataSnapshot)
        fun onChildChanged(dataSnapshot: DataSnapshot)
        fun onChildMoved(dataSnapshot: DataSnapshot)
        fun onChildRemoved(dataSnapshot: DataSnapshot)
    }


    private var query: Query? = null
    private val uid = FirebaseAuth.getInstance().currentUser?.uid
    private var requestCallbacks: DbChildCallbacks? = null

    fun getChatListData(limitToLast: Int, mLastKey: String, more: Boolean, mRequestCallbacks: DbChildCallbacks) {
        requestCallbacks = mRequestCallbacks
        query = if (more) {
            FirebaseDatabase.getInstance().reference
                    .child(ApiConstants.USERS_CHAT_LIST)
                    .child(uid!!)
                    .orderByChild(DbConstants.TIMESTAMP)
                    .orderByKey().endAt(mLastKey)
                    .limitToLast(limitToLast)
        } else {
            FirebaseDatabase.getInstance().reference
                    .child(ApiConstants.USERS_CHAT_LIST)
                    .child(uid!!)
                    .orderByChild(DbConstants.TIMESTAMP)
                    .limitToLast(limitToLast)
        }
        startListener()
    }

    fun getConversationData(limitToLast: Int, nodeId: String, more: Boolean, mChatUserId: String, mRequestCallbacks: DbChildCallbacks) {
        requestCallbacks = mRequestCallbacks
        query = if (more) {
            FirebaseDatabase.getInstance().reference
                    .child(ApiConstants.USERS_CONVERSATION_LIST)
                    .child(uid!!)
                    .child(mChatUserId)
                    .orderByKey().endAt(nodeId)
                    .limitToLast(limitToLast)
        } else {
            FirebaseDatabase.getInstance().reference
                    .child(ApiConstants.USERS_CONVERSATION_LIST)
                    .child(uid!!)
                    .child(mChatUserId)
                    .limitToLast(limitToLast)
        }

        startListener()
    }

    fun getUsersProfile(mRequestCallbacks: DbChildCallbacks) {
        query = FirebaseDatabase.getInstance().reference.child(ApiConstants.USERS_PROFILE)
        startListener()
        requestCallbacks = mRequestCallbacks
    }

    private fun startListener() {
        query!!.keepSynced(true)
        query!!.addChildEventListener(childEventListener)
    }

    private val childEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            requestCallbacks?.onChildAdded(dataSnapshot)
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
            requestCallbacks?.onChildChanged(dataSnapshot)
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            requestCallbacks?.onChildRemoved(dataSnapshot)
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
            requestCallbacks?.onChildMoved(dataSnapshot)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            requestCallbacks?.onCancelled(databaseError)
        }
    }
}
