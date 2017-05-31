package com.dinosys.sportbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.signin.SignInFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
