package com.dinosys.sportbook.application

import android.app.Application
import com.dinosys.sportbook.di.AppModule
import com.dinosys.sportbook.di.authentication.AuthenticationComponent
import com.dinosys.sportbook.di.authentication.DaggerAuthenticationComponent
import com.dinosys.sportbook.di.team.DaggerTeamComponent
import com.dinosys.sportbook.di.team.TeamComponent
import com.dinosys.sportbook.di.tournament.DaggerTournamentComponent
import com.dinosys.sportbook.di.tournament.TournamentComponent

class SportbookApp: Application() {

    companion object {
        lateinit var authComponent: AuthenticationComponent
        lateinit var tournamentComponent: TournamentComponent
        lateinit var teamComponent: TeamComponent

    }

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    fun initComponents() {
        authComponent = DaggerAuthenticationComponent
                    .builder().build()
        tournamentComponent = DaggerTournamentComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        teamComponent = DaggerTeamComponent
                .builder()
                .build()
    }
}