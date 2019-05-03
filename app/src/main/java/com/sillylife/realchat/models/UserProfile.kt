package com.sillylife.realchat.models

import android.os.Parcel
import android.os.Parcelable

class UserProfile() : Parcelable {

    var userNumber: String? = null
    var userName: String? = null
    var userImage: String? = null
    var onlineStatus: Boolean? = null
    var userId: String? = null

    constructor(parcel: Parcel) : this() {
        userNumber = parcel.readString()
        userName = parcel.readString()
        userImage = parcel.readString()
        onlineStatus = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        userId = parcel.readString()
    }

    constructor(userNumber: String?, userName: String?, userImage: String?, onlineStatus: Boolean?, userId: String?) : this() {
        this.userNumber = userNumber
        this.userName = userName
        this.userImage = userImage
        this.onlineStatus = onlineStatus
        this.userId = userId
    }

    constructor(userNumber: String?, userName: String?, userId: String?, userImage: String?) : this() {
        this.userNumber = userNumber
        this.userName = userName
        this.userImage = userImage
        this.onlineStatus = onlineStatus
        this.userId = userId
    }

    constructor(userNumber: String?, userName: String?, userId: String?) : this() {
        this.userNumber = userNumber
        this.userName = userName
        this.userImage = userImage
        this.onlineStatus = onlineStatus
        this.userId = userId
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userNumber)
        parcel.writeString(userName)
        parcel.writeString(userImage)
        parcel.writeValue(onlineStatus)
        parcel.writeString(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserProfile> {
        override fun createFromParcel(parcel: Parcel): UserProfile {
            return UserProfile(parcel)
        }

        override fun newArray(size: Int): Array<UserProfile?> {
            return arrayOfNulls(size)
        }
    }
}
