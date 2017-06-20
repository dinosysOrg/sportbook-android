package com.dinosys.sportbook.di.tournament

import com.dinosys.sportbook.di.AppModule
import com.dinosys.sportbook.di.NetworkAuthorizationModule
import com.dinosys.sportbook.features.mytournament.MyTournamentFragment
import com.dinosys.sportbook.features.mytournament.detail.TournamentDetailFragment
import com.dinosys.sportbook.features.tournament.TournamentFragment
import com.dinosys.sportbook.features.tournament.signup.TournamentSignUpFragment
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
    fun inject(mytournamentFragment: MyTournamentFragment)
    fun inject(tournamentDetailFragment: TournamentDetailFragment)
    fun inject(tournamentSignUpFragment: TournamentSignUpFragment)
}