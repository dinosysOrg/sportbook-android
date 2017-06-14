package com.dinosys.sportbook.features.tournament

import android.content.Context
import com.dinosys.sportbook.extensions.throwable
import com.dinosys.sportbook.networks.models.AuthModel
import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject


class TournamentViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getTournaments(context: Context?, authModel: AuthModel?) : Observable<Response<TournamentModel>> {
        if (authModel == null || authModel.header == null) {
            return Observable.error("authentication header data is null".throwable)
        }
        return tournamentAPI.getTournaments()
    }
}