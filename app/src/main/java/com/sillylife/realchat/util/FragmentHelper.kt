package com.sillylife.realchat.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created on 26/09/18.
 */
object FragmentHelper {

    const val HOME = "home"
    const val LIBRARY = "library"
    const val GROUP = "group"
    const val PROFILE = "profile"
    const val TO_CHANNEL = "to_channel"
    const val HOME_TO_CHANNEL = "home_to_channel"
    const val FAVOURITES_TO_CHANNEL = "favourites_to_channel"
    const val HOME_TO_GENRE_CONTENT_TYPE = "home_to_genre_content_type"
    const val HOME_TO_CONTENT_TYPE_GENRE = "home_to_content_type_genre"
    const val HOME_TO_GENRE = "home_to_genre"
    const val PROFILE_TO_SUBSCRIBED_CHANNEL_LIST = "profile_to_subscribed_channel_list"
    const val PROFILE_TO_FANS_LIST = "profile_to_fans_list"
    const val LIBRARY_TO_MYDOWNLOADS = "library_to_mydownloads"
    const val PLAYER_TO_COMMENTS = "player_to_comments"
    const val PLAYER_TO_PROFILE = "player_to_profile"
    const val PLAYER_TO_CHANNEL = "player_to_channel"

    fun replace(@IdRes containerId: Int, fragmentManager: FragmentManager, fragment: Fragment, tag: String) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment, tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun add(@IdRes containerId: Int, fragmentManager: FragmentManager, fragment: Fragment, tag: String) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, fragment, tag)
        fragmentTransaction.addToBackStack(tag)

        val displayedFragment = fragmentManager.findFragmentById(containerId)

        if (displayedFragment != null) {
            fragmentTransaction.hide(displayedFragment)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    fun add(@IdRes containerId: Int, fragmentManager: FragmentManager, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, fragment)

        val displayedFragment = fragmentManager.findFragmentById(containerId)

        if (displayedFragment != null) {
            fragmentTransaction.hide(displayedFragment)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }
}