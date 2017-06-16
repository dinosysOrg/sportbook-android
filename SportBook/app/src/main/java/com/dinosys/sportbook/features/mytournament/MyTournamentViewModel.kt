package com.dinosys.sportbook.features.mytournament

import android.content.Context
import com.dinosys.sportbook.extensions.throwable
import com.dinosys.sportbook.networks.models.AuthModel
import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MyTournamentViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getMyTournamentList(context: Context?, authModel: AuthModel?): Observable<Response<TournamentModel>> {

        if (authModel == null || authModel.header == null) {
            return Observable.error { "authentication header data is null ".throwable }
        }

        val header = authModel.header!!
        return tournamentAPI.getTournaments(header.get("Access-Token"),
                header.get("Client"),
                header.get("Expiry"),
                header.get("Token-Type"),
                header.get("Uid"))

    }
}