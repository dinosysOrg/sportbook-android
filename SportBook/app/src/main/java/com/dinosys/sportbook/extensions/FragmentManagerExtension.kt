package com.dinosys.sportbook.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.signin.SignInFragment

fun FragmentManager.openScreenByTag(tag:String, containerId: Int = R.id.ll_main_container, needFindInStack: Boolean = false) {
    val fragment = this.createFragmentByTAG(tag)
    when(fragment) {
        null -> Log.e("FragmentManager", "Can not create fragment by tag ${tag}")
        else -> attachFragment(containerId, fragment, SignInFragment.TAG, needFindInStack)
    }
}

private fun FragmentManager.createFragmentByTAG(tag: String) : Fragment? =
    when(tag) {
        SignInFragment.TAG -> SignInFragment()
        else -> null
    }


private fun FragmentManager.addNewFragment(containerId: Int, fragment: Fragment, tag: String) {
    val transaction = this.beginTransaction()
    transaction.add(containerId, fragment, tag)
            .addToBackStack(tag)
            .commit()
}

private fun FragmentManager.attachFragment(containerId: Int,
                                   fragment: Fragment,
                                   tag: String,
                                   needFindInStack: Boolean) {
    when (needFindInStack) {
        false -> {
            this.addNewFragment(containerId, fragment, tag)
        }
        else -> {
            val previousFragment = this.findFragmentByTag(tag)
            if (previousFragment == null) {
                this.addNewFragment(containerId, fragment, tag)
            } else {
                this.beginTransaction().show(previousFragment).commit()
            }
        }
    }
}
