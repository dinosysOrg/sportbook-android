package com.dinosys.sportbook.di.team

import com.dinosys.sportbook.networks.teams.TeamsAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TeamModule {

    @Singleton
    @Provides
    fun provideTeamAPI(retrofit: Retrofit): TeamsAPI {
        return retrofit.create(TeamsAPI::class.java)
    }
}