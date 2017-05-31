package com.dinosys.sportbook.di.authentication

import com.dinosys.sportbook.networks.authentication.AuthenticationAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AuthenticationModule {

    @Singleton
    @Provides
    fun provideAuthenticationAPI(retrofit: Retrofit) : AuthenticationAPI {
        return retrofit.create(AuthenticationAPI::class.java)
    }

}
