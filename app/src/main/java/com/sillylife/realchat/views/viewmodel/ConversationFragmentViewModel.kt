package com.sillylife.realchat.views.viewmodel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.sillylife.realchat.models.User
import com.sillylife.realchat.views.fragments.BaseFragment
import com.sillylife.realchat.views.module.BaseModule
import com.sillylife.realchat.views.module.ConversationFragmentModule

class ConversationFragmentViewModel(fragment: BaseFragment) : BaseViewModel(), ConversationFragmentModule.IModuleListener {
    override fun onGetMeSuccess(user: User?) {
        viewListener.onGetMeSuccess(user)
    }

    override fun onGetMeFailure(databaseError: DatabaseError) {
        viewListener.onGetMeFailure(databaseError)
    }

    override fun onChildAdded(dataSnapshot: DataSnapshot, hasMore: Boolean?) {
        viewListener.onChildAdded(dataSnapshot, hasMore)
    }

    override fun onCancelled(databaseError: DatabaseError) {
        viewListener.onCancelled(databaseError)
    }

    override fun onChildChanged(dataSnapshot: DataSnapshot) {
        viewListener.onChildChanged(dataSnapshot)
    }

    override fun onChildMoved(dataSnapshot: DataSnapshot) {
        viewListener.onChildMoved(dataSnapshot)
    }

    override fun onChildRemoved(dataSnapshot: DataSnapshot) {
        viewListener.onChildRemoved(dataSnapshot)
    }

    val module = ConversationFragmentModule(this)
    private val viewListener = fragment as ConversationFragmentModule.IModuleListener

    override fun setViewModel(): BaseModule {
        return module
    }

    fun getConversationData(limitToLast: Int, lastNodeId: String, hasMore: Boolean?, otherUid: String?) {
        module.loadChatList(limitToLast, lastNodeId, hasMore, otherUid)
    }

    fun getMe(){
        module.getMe()
    }
}
