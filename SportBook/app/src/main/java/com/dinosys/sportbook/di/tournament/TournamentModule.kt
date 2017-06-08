package com.dinosys.sportbook.di.tournament

import com.dinosys.sportbook.networks.tournament.TournamentAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TournamentModule {

    @Singleton
    @Provides
    fun provideAuthenticationAPI(retrofit: Retrofit) : TournamentAPI {
        return retrofit.create(TournamentAPI::class.java)
    }

}