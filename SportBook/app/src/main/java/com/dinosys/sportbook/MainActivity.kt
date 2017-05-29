package com.dinosys.sportbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.dinosys.sportbook.features.signin.SignInFragment
import com.dinosys.sportbook.managers.FragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openLoginScreen()
    }

    fun openLoginScreen() {
        FragmentManager.add(this, R.id.ll_main_container,
                SignInFragment(), SignInFragment.TAG)
    }

}
