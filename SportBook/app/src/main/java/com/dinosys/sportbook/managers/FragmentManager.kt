package com.dinosys.sportbook.managers

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

object FragmentManager {

    fun add(context: AppCompatActivity, containerId: Int, fragment: Fragment, tag: String) {
        context.supportFragmentManager
                .beginTransaction()
                .add(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit()
    }

    fun remove(activity: AppCompatActivity, fragment: Fragment) {
        activity.supportFragmentManager
                .beginTransaction()
                .remove(fragment).commit()
    }

}
