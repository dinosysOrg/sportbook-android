package com.dinosys.sportbook.features.mytournament

import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MyTournamentViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getMyTournamentList(): Observable<Response<TournamentModel>> {
        return tournamentAPI.getMyTournaments()
    }

}