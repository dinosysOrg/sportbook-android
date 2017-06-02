package com.dinosys.sportbook.di.authentication

import com.dinosys.sportbook.di.NetworkModule
import com.dinosys.sportbook.features.signin.ForgotFragment
import com.dinosys.sportbook.features.signin.SignInFragment
import com.facebook.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AuthenticationModule::class,
        NetworkModule::class)
)
interface AuthenticationComponent {
        fun inject(singInfragment: SignInFragment)
        fun inject(forgotFragment: ForgotFragment)
}