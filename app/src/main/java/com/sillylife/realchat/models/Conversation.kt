package com.sillylife.realchat.models

import android.os.Parcel
import android.os.Parcelable

class Conversation() : Parcelable {

    var convMessage: String? = null
    var convId: String? = null
    var convTimestamp: String? = null
    var convIsSelf: Boolean? = null
    var convUser: ConvUser? = null
    var convFile: ConvFile? = null

    constructor(parcel: Parcel) : this() {
        convMessage = parcel.readString()
        convId = parcel.readString()
        convTimestamp = parcel.readString()
        convIsSelf = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        convUser = parcel.readParcelable(ConvUser::class.java.classLoader)
        convFile = parcel.readParcelable(ConvFile::class.java.classLoader)
    }

    constructor(convId: String?, convMessage: String?, convTimestamp: String?, convIsSelf: Boolean, convUser: ConvUser?) : this() {
        this.convId = convId
        this.convMessage = convMessage
        this.convTimestamp = convTimestamp
        this.convUser = convUser
        this.convIsSelf = convIsSelf
    }

    constructor(convMessage: String?, convTimestamp: String?, convUser: ConvUser?, convFile: ConvFile) : this() {
        this.convMessage = convMessage
        this.convTimestamp = convTimestamp
        this.convUser = convUser
        this.convFile = convFile
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(convMessage)
        parcel.writeValue(convId)
        parcel.writeString(convTimestamp)
        parcel.writeValue(convIsSelf)
        parcel.writeParcelable(convUser, flags)
        parcel.writeParcelable(convFile, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Conversation> {
        override fun createFromParcel(parcel: Parcel): Conversation {
            return Conversation(parcel)
        }

        override fun newArray(size: Int): Array<Conversation?> {
            return arrayOfNulls(size)
        }
    }


}