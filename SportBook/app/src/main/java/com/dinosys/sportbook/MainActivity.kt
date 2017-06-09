package com.dinosys.sportbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.signin.SignInFragment
import com.dinosys.sportbook.features.tournament.ProfileFragment
import com.dinosys.sportbook.features.tournament.StatisticFragment
import com.dinosys.sportbook.features.tournament.TournamentFragment
import com.dinosys.sportbook.managers.AuthenticationManager
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Fabric.with(this, Crashlytics())
        initViews()
        initListeners()
    }

    private fun initViews() {
        val auth = AuthenticationManager.getUser(applicationContext)
        when (auth) {
            null -> supportFragmentManager.openScreenByTag(SignInFragment.TAG)
            else -> loadTabContentDefaultSelected()
        }
    }

    fun loadTabContentDefaultSelected() {
        navigation.selectedItemId = R.id.navigation_tournament
    }

    private fun initListeners() {
        navigation.setOnNavigationItemSelectedListener {
            item -> when(item.itemId) {
                R.id.navigation_tournament -> {
                    supportFragmentManager.openScreenByTag(tag = TournamentFragment.TAG,
                            containerId = R.id.flMainTabContainer, needFindInStack = true)
                    true
                }
                R.id.navigation_profile -> {
                    supportFragmentManager.openScreenByTag(tag = ProfileFragment.TAG,
                            containerId = R.id.flMainTabContainer, needFindInStack = true)
                    true
                }
                else -> {
                    supportFragmentManager.openScreenByTag(tag = StatisticFragment.TAG,
                            containerId = R.id.flMainTabContainer, needFindInStack = true)
                    true
                }
            }
        }
    }
}
