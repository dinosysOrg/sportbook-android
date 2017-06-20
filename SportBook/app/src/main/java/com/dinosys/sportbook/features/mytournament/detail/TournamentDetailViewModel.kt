package com.dinosys.sportbook.features.mytournament.detail

import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class TournamentDetailViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getTournamentById(tournamentId: Int): Observable<Response<TournamentDataModel>> {
        return tournamentAPI.getTournamentDetail(tournamentId)
    }

}