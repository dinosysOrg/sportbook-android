package com.dinosys.sportbook.components

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class BaseTabViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList: ArrayList<Fragment>?

    private val fragmentTitleList: ArrayList<String>

    init {
        this.fragmentList = ArrayList()
        this.fragmentTitleList = ArrayList()
    }

    fun addFragments(fragment: Fragment, title: String) {
        this.fragmentList!!.add(fragment)
        this.fragmentTitleList.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }

    override fun getCount(): Int = fragmentList?.size?: 0

}