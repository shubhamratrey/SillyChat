package com.sillylife.realchat.models

import android.os.Parcel
import android.os.Parcelable

class ChatItem() : Parcelable {

    var timestamp: String? = null
    var read: Boolean? = null
    var unReadMessageCount: Long? = null
    var lastMessage: String? = null
    var messageType: String? = null
    var uid: String? = null
    var convUser: ConvUser? = null

    constructor(parcel: Parcel) : this() {
        timestamp = parcel.readString()
        read = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        unReadMessageCount = parcel.readValue(Long::class.java.classLoader) as? Long
        lastMessage = parcel.readString()
        messageType = parcel.readString()
        uid = parcel.readString()
        convUser = parcel.readParcelable(ConvUser::class.java.classLoader)
    }

    constructor(timestamp: String?, read: Boolean?, unReadMessageCount: Long?, lastMessage: String?, messageType: String?, uid: String?, convUser: ConvUser?) : this() {
        this.timestamp = timestamp
        this.read = read
        this.unReadMessageCount = unReadMessageCount
        this.lastMessage = lastMessage
        this.messageType = messageType
        this.uid = uid
        this.convUser = convUser
    }

    constructor(timestamp: String?, uid: String?, convUser: ConvUser?) : this() {
        this.timestamp = timestamp
        this.read = read
        this.unReadMessageCount = unReadMessageCount
        this.lastMessage = lastMessage
        this.messageType = messageType
        this.uid = uid
        this.convUser = convUser
    }

    constructor(timestamp: String?, uid: String?, lastMessage: String?, messageType: String?, convUser: ConvUser?) : this() {
        this.timestamp = timestamp
        this.read = read
        this.unReadMessageCount = unReadMessageCount
        this.lastMessage = lastMessage
        this.messageType = messageType
        this.uid = uid
        this.convUser = convUser
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(timestamp)
        parcel.writeValue(read)
        parcel.writeValue(unReadMessageCount)
        parcel.writeString(lastMessage)
        parcel.writeString(messageType)
        parcel.writeString(uid)
        parcel.writeParcelable(convUser, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChatItem> {
        override fun createFromParcel(parcel: Parcel): ChatItem {
            return ChatItem(parcel)
        }

        override fun newArray(size: Int): Array<ChatItem?> {
            return arrayOfNulls(size)
        }
    }
}
