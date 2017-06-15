package com.dinosys.sportbook.features.tournament

import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject


class TournamentViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getTournaments() : Observable<Response<TournamentModel>> {
        return tournamentAPI.getTournaments()
    }
}