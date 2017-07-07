package com.dinosys.sportbook.features.tournament.overview

import com.dinosys.sportbook.networks.models.AuthDataModel
import com.dinosys.sportbook.networks.models.TournamentDetailDataModel
import com.dinosys.sportbook.networks.models.TournamentSignUpModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class TournamentOverviewViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getTournamentDetail(idTournament: Int) : Observable<Response<TournamentDetailDataModel>> {
        return tournamentAPI.getTournamentDetail(idTournament)
    }

    fun signUpTournament(idTournament: Int, authModel: AuthDataModel) : Observable<Response<TournamentSignUpModel>> {
        return tournamentAPI.signUpTournament(idTournament = idTournament,
                name = authModel.name!!,
                phoneNumber = authModel.phoneNumber!!,
                address = authModel.address!!,
                skillId = authModel.skillId)
    }

}