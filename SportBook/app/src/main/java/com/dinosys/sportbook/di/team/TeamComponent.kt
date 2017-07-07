package com.dinosys.sportbook.di.team

import com.dinosys.sportbook.di.AppModule
import com.dinosys.sportbook.di.NetworkAuthorizationModule
import com.dinosys.sportbook.di.NetworkModule
import com.dinosys.sportbook.features.mytournament.venue.VenueFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        TeamModule::class,
        NetworkAuthorizationModule::class)
)
interface TeamComponent{

    fun inject(venueFragment: VenueFragment)

}
