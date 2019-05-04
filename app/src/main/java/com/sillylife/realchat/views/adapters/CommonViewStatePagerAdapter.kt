package com.sillylife.realchat.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class CommonViewStatePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val mFragmentList: ArrayList<Fragment> = ArrayList()
    private val mFragmentTitleList: ArrayList<String> = ArrayList()

    fun addItem(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun getFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun getPositionByTitle(title: String): Int {
        var position = -1
        for (i in mFragmentTitleList.indices) {
            if (mFragmentTitleList[i].equals(title, true)) {
                position = i
                break
            }
        }
        return position
    }

    fun clear() {
        mFragmentList.clear()
        mFragmentTitleList.clear()
    }

    fun getFragments(): ArrayList<Fragment> {
        return mFragmentList
    }

}