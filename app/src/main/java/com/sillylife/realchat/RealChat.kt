package com.sillylife.realchat

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RealChat : Application() {
    private val mUserDatabase: DatabaseReference? = null
    private var mAuth: FirebaseAuth? = null
    private val mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        realChat = this@RealChat

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        //mAuth = FirebaseAuth.getInstance()


        //		if(mAuth.getCurrentUser() != null) {
        //			mUserDatabase = FirebaseDatabase.getInstance().getReference()
        //					.child(getString(R.string.dbname_users))
        //					.child(getString(R.string.dbname_profile))
        //					.child(mAuth.getCurrentUser().getUid());
        //
        //			mUserDatabase.addValueEventListener(new ValueEventListener() {
        //				@Override
        //				public void onDataChange(DataSnapshot dataSnapshot) {
        //
        //					if (dataSnapshot != null) {
        //
        //						mUserDatabase.child("online").onDisconnect().setValue(ServerValue.TIMESTAMP);
        //
        //					}
        //
        //				}
        //
        //				@Override
        //				public void onCancelled(DatabaseError databaseError) {
        //
        //				}
        //			});
        //
        //		}


    }

    companion object {
        @Volatile
        private var realChat: RealChat? = null

        @Synchronized
        fun getInstance(): RealChat {
            return realChat!!
        }
    }
}
