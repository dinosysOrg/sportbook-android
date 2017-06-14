package com.dinosys.sportbook.di.tournament

import com.dinosys.sportbook.di.AppModule
import com.dinosys.sportbook.di.NetworkAuthorizationModule
import com.dinosys.sportbook.features.tournament.TournamentFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        TournamentModule::class,
        NetworkAuthorizationModule::class)
)
interface TournamentComponent {
    fun inject(tournamentFragment: TournamentFragment)
}