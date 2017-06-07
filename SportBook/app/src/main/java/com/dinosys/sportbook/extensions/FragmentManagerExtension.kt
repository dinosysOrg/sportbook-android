package com.dinosys.sportbook.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.mytournament.MyTournamentFragment
import com.dinosys.sportbook.features.signin.SignInFragment
import com.dinosys.sportbook.features.tournament.ProfileFragment
import com.dinosys.sportbook.features.tournament.StatisticFragment
import com.dinosys.sportbook.features.tournament.TournamentFragment

fun FragmentManager.openScreenByTag(tag:String, containerId: Int = R.id.ll_main_container, needFindInStack: Boolean = false) {
    val fragment = createFragmentByTAG(tag)
    when(fragment) {
        null -> Log.e("FragmentManager", "Can not create fragment by tag ${tag}")
        else -> attachFragment(containerId, fragment, tag, needFindInStack)
    }
}

private fun FragmentManager.createFragmentByTAG(tag: String) : Fragment? =
    when(tag) {
        SignInFragment.TAG -> SignInFragment()
        StatisticFragment.TAG -> StatisticFragment()
        ProfileFragment.TAG -> ProfileFragment()
        TournamentFragment.TAG -> TournamentFragment()
        MyTournamentFragment.TAG -> MyTournamentFragment()
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
                this.beginTransaction()
                        .replace(containerId, previousFragment, tag)
                        .addToBackStack(tag)
                        .commit()
            }
        }
    }
}
