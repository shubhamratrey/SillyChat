package com.sillylife.realchat.service.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sillylife.realchat.constants.ApiConstants


class DBResponses {
    companion object {

        @JvmStatic
        fun getInstance(): DBResponses {
            var instance: DBResponses? = null
            if (instance == null) {
                instance = DBResponses()
            }
            return instance
        }
    }

    interface DbCallbacks {
        fun onSuccess(dataSnapshot: DataSnapshot)
        fun onCancelled(databaseError: DatabaseError)
    }

    private var query: Query? = null
    private var mRequestCallbacks: DbCallbacks? = null
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    fun getSelfProfile(requestCallbacks: DbCallbacks) {
        query = FirebaseDatabase.getInstance().reference.child(ApiConstants.USERS_PROFILE).child(uid!!)
        startListener()
        mRequestCallbacks = requestCallbacks
    }

    fun getUserProfile(uid: String, requestCallbacks: DbCallbacks) {
        query = FirebaseDatabase.getInstance().reference.child(ApiConstants.USERS_PROFILE).child(uid)
        startListener()
        mRequestCallbacks = requestCallbacks
    }

    fun getUsersProfile(requestCallbacks: DbCallbacks) {
        query = FirebaseDatabase.getInstance().reference.child(ApiConstants.USERS_PROFILE)
        startListener()
        mRequestCallbacks = requestCallbacks
    }

    fun getOrigin(requestCallbacks: DbCallbacks) {
        mRequestCallbacks = requestCallbacks
        query = FirebaseDatabase.getInstance().reference
        startListener()
    }

    private fun startListener() {
        query!!.keepSynced(true)
        query!!.addListenerForSingleValueEvent(valueEventListener)
    }

    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            mRequestCallbacks?.onSuccess(dataSnapshot)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            mRequestCallbacks?.onCancelled(databaseError)
        }
    }
}
