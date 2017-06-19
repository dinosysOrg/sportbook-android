package com.dinosys.sportbook.features.tournament.signup

import com.dinosys.sportbook.networks.models.SkillModel
import com.dinosys.sportbook.networks.tournament.TournamentAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class TournamentSignUpViewModel @Inject constructor(val tournamentAPI: TournamentAPI) {

    fun getSkills() : Observable<Response<SkillModel>> {
        return tournamentAPI.getSkills()
    }
}