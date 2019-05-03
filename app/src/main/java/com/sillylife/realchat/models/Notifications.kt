package com.sillylife.realchat.models

import android.os.Parcel
import android.os.Parcelable

class Notifications() : Parcelable {
    var userPhoto: String? = null
    var userName: String? = null
    var userID: String? = null
    var message: String? = null

    constructor(parcel: Parcel) : this() {
        userPhoto = parcel.readString()
        userName = parcel.readString()
        userID = parcel.readString()
    }

    constructor(userPhoto: String?, userName: String?, userID: String?, message: String?) : this() {
        this.userPhoto = userPhoto
        this.userName = userName
        this.userID = userID
        this.message = message
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userPhoto)
        parcel.writeString(userName)
        parcel.writeString(userID)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notifications> {
        override fun createFromParcel(parcel: Parcel): Notifications {
            return Notifications(parcel)
        }

        override fun newArray(size: Int): Array<Notifications?> {
            return arrayOfNulls(size)
        }
    }


}