package com.sillylife.realchat.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DimenRes
import com.sillylife.realchat.RealChat
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object CommonUtil {

    val context = RealChat.getInstance()
    var priorityAppList: MutableList<String> = ArrayList()

    /**
     * convert dimens to exact pixels
     */
    fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp.toFloat() * density)
    }

    /**
     * Checks whether text is null or empty or not
     */
    fun textIsEmpty(value: String?): Boolean {

        if (value == null)
            return true

        var empty = false

        val message = value.trim { it <= ' ' }

        if (message.isEmpty()) {
            empty = true
        }

        val isWhitespace = message.matches("^\\s*$".toRegex())

        if (isWhitespace) {
            empty = true
        }

        return empty
    }

    @Throws(IOException::class)
    fun createImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    fun getDimensionPixelSize(@DimenRes dimenRes: Int): Int {
        return context.resources.getDimensionPixelSize(dimenRes)
    }

    fun hideKeyboard(context: Context) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val v = (context as Activity).currentFocus ?: return
        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
    }

    fun isAppInstalled(context: Context, uri: String): Boolean {
        val pm = context.packageManager
        var app_installed: Boolean
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            app_installed = true
        } catch (e: PackageManager.NameNotFoundException) {
            app_installed = false
        } catch (e: RuntimeException) {
            app_installed = false
        }

        return app_installed
    }


    fun getColumnWidthForGrid(context: Context, numberOfColumn: Int, verticalPadding: Int): Int {
        val screenWidth = context.resources.displayMetrics.widthPixels
        return screenWidth / numberOfColumn - verticalPadding * numberOfColumn
    }

    fun coolFormat(n: Double, iteration: Int): String {

        if (n < 1000)
            return n.toInt().toString()

        val c = charArrayOf('k', 'm', 'b', 't')
        val d = n.toLong() / 100 / 10.0
        val isRound = d * 10 % 10 == 0.0//true if the decimal part is equal to 0 (then it's trimmed anyway)
        return (if (d < 1000)
        //this determines the class, i.e. 'k', 'm' etc
            ((if (d > 99.9 || isRound || (!isRound && d > 9.99))
            //this decides whether to trim the decimals
                d.toInt() * 10 / 10
            else
                (d).toString() + "" // (int) d * 10 / 10 drops the decimal
                    )).toString() + "" + c[iteration]
        else
            coolFormat(d, iteration + 1))
    }

    //fun dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

}