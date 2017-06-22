package com.dinosys.sportbook.features.tournament.overview

import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class TournamentOverviewViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getTournamentDetail(idTournament: Int) : Observable<Response<TournamentDataModel>> {
        return tournamentAPI.getTournamentDetail(idTournament)
    }

}