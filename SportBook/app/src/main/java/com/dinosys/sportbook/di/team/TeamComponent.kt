package com.dinosys.sportbook.di.team

import com.dinosys.sportbook.di.NetworkModule
import com.dinosys.sportbook.features.mytournament.venue.TimeRankVenueFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        TeamModule::class,
        NetworkModule::class)
)
interface TeamComponent{
    fun inject(timeRankVenueFragment: TimeRankVenueFragment)
}
