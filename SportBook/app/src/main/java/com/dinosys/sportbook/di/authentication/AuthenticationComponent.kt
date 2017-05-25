package com.dinosys.sportbook.di.authentication

import com.dinosys.sportbook.di.NetworkModule
import com.dinosys.sportbook.fragments.SignInFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AuthenticationModule::class,
        NetworkModule::class)
)
interface AuthenticationComponent {
        fun inject(singInfragment: SignInFragment)
}