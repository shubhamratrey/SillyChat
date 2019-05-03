package com.sillylife.realchat.models

import android.os.Parcel
import android.os.Parcelable

class ConvFile() : Parcelable {

    var fileType: String? = null
    var fileUrl: String? = null
    var fileName: String? = null
    var fileSize: String? = null

    constructor(parcel: Parcel) : this() {
        fileType = parcel.readString()
        fileUrl = parcel.readString()
        fileName = parcel.readString()
        fileSize = parcel.readString()
    }

    constructor(fileType: String?, fileUrl: String?, fileName: String?, fileSize: String?) : this() {
        this.fileType = fileType
        this.fileUrl = fileUrl
        this.fileName = fileName
        this.fileSize = fileSize
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fileType)
        parcel.writeString(fileUrl)
        parcel.writeString(fileName)
        parcel.writeString(fileSize)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConvFile> {
        override fun createFromParcel(parcel: Parcel): ConvFile {
            return ConvFile(parcel)
        }

        override fun newArray(size: Int): Array<ConvFile?> {
            return arrayOfNulls(size)
        }
    }
}