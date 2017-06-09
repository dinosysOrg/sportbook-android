package com.dinosys.sportbook.di.tournament

import com.dinosys.sportbook.di.NetworkModule
import com.dinosys.sportbook.features.tournament.TournamentFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        TournamentModule::class,
        NetworkModule::class)
)
interface TournamentComponent {
    fun inject(tournamentFragment: TournamentFragment)
}