package com.sillylife.realchat.models

import android.os.Parcel
import android.os.Parcelable

class ConvUser() : Parcelable {
    var userPhoto: String? = null
    var userName: String? = null
    var userID: String? = null

    constructor(parcel: Parcel) : this() {
        userPhoto = parcel.readString()
        userName = parcel.readString()
        userID = parcel.readString()
    }

    constructor(userPhoto: String?, userName: String?, userID: String?) : this() {
        this.userPhoto = userPhoto
        this.userName = userName
        this.userID = userID
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userPhoto)
        parcel.writeString(userName)
        parcel.writeString(userID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConvUser> {
        override fun createFromParcel(parcel: Parcel): ConvUser {
            return ConvUser(parcel)
        }

        override fun newArray(size: Int): Array<ConvUser?> {
            return arrayOfNulls(size)
        }
    }
}