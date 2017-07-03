package com.dinosys.sportbook.features.mytournament.opponent

import com.dinosys.sportbook.networks.models.OpponentListModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class OpponentViewModel@Inject constructor(val tournamentAPI: TournamentAPI){

    fun getOpponentList(tournamentId: Int): Observable<Response<OpponentListModel>> {
        return tournamentAPI.getOpponentList(tournamentId = tournamentId)
    }

}