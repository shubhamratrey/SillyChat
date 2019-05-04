package com.sillylife.realchat.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sillylife.realchat.R
import com.sillylife.realchat.constants.BundleConstants
import com.sillylife.realchat.constants.DefaultFragment
import com.sillylife.realchat.util.FragmentHelper

class ContainerFragment : BaseFragment() {

    var showLanguageLayout: Boolean? = null
    var homeFragment: HomeFragment? = null
    var defaultFragment: DefaultFragment? = null

    companion object {
        fun newInstance(defaultFragment: DefaultFragment): ContainerFragment {
            val fragment = ContainerFragment()
            val bundle = Bundle()
            bundle.putString(BundleConstants.DEFAULT_FRAGMENT, defaultFragment.name)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments!!.getString(BundleConstants.DEFAULT_FRAGMENT, "")
        defaultFragment = DefaultFragment.valueOf(type)
        when (defaultFragment) {
            DefaultFragment.HOME -> {
                homeFragment = HomeFragment.newInstance()
                replaceFragment(homeFragment!!, FragmentHelper.HOME)
            }
//            DefaultFragment.GROUP -> {
//                libraryFragment = LibraryFragment.newInstance()
//                addFragment(libraryFragment!!, FragmentHelper.GROUP)
//            }
//            DefaultFragment.PROFILE -> {
//                profileFragment = ProfileFragment.newInstance(true)
//                addFragment(profileFragment!!, FragmentHelper.PROFILE)
//            }
        }
    }



}