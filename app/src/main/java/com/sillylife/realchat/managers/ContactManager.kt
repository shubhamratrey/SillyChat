package com.sillylife.realchat.managers

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import com.sillylife.realchat.models.User
import java.util.*


object ContactManager {

    fun getContactNameByNumber(phoneNumber: String, context: Context): String {
        val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))

        val projection = arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME)

        var contactName = ""
        val cursor = context.contentResolver.query(uri, projection, null, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactName = cursor.getString(0)
            }
            cursor.close()
        }

        return contactName
    }


    fun getContactNumberByName(name: String, context: Context): String? {
        val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(name))

        val projection = arrayOf(ContactsContract.PhoneLookup.NUMBER)

        var number = ""
        val cursor = context.contentResolver.query(uri, projection, null, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                number = cursor.getString(0)
            }
            cursor.close()
        }

        return number
    }

    fun removeDuplicates(list: ArrayList<User>): ArrayList<User> {
        val items: ArrayList<User> = ArrayList()
        if (list.isEmpty()) {
            return items
        }
        val set = TreeSet(Comparator<User> { o1, o2 ->
            if ((o1 as User).userNumber.equals((o2 as User).userNumber)) {
                0
            } else 1
        })
        set.addAll(list)
        return ArrayList(set)
    }
}
