package com.dinosys.sportbook.application

import android.app.Application
import com.dinosys.sportbook.di.authentication.AuthenticationComponent
import com.dinosys.sportbook.di.authentication.DaggerAuthenticationComponent

class SportbookApp: Application() {

    companion object {
        lateinit var authComponent: AuthenticationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    fun initComponents() {
        authComponent = DaggerAuthenticationComponent
                    .builder().build()
    }
}