package com.dinosys.sportbook.networks.tournament

import com.dinosys.sportbook.networks.models.SkillModel
import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.models.TournamentModel
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface TournamentAPI {

    @GET("tournaments")
    fun getTournaments(): Observable<Response<TournamentModel>>

    @GET("tournaments/{tournamentId}/teams")
    fun signUpTournament(@Path("tournamentId") idTournament: Int?): Observable<Response<JSONObject>>

    @GET("skills")
    fun getSkills(): Observable<Response<SkillModel>>

    @GET("tournaments/{tournamentId}")
    fun getTournamentDetail(@Path("tournamentId") idTournament: Int?): Observable<Response<TournamentDataModel>>
}