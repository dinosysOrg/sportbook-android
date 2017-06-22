package com.dinosys.sportbook.networks.tournament

import com.dinosys.sportbook.networks.models.SkillModel
import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.models.TournamentModel
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*


interface TournamentAPI {

    @GET("tournaments")
    fun getTournaments(): Observable<Response<TournamentModel>>

    @GET("tournaments/my-tournaments")
    fun getMyTournaments(): Observable<Response<TournamentModel>>

    @FormUrlEncoded
    @POST("tournaments/{tournamentId}/teams")
    fun signUpTournament(@Path("tournamentId") idTournament: Int?,
                         @Field("name") name: String,
                         @Field("phone_number") phoneNumber: String,
                         @Field("address") address: String,
                         @Field("club") club: String? = null,
                         @Field("birthday") birthday: String? = null,
                         @Field("user_ids") userIds: Array<Int>? = null): Observable<Response<JSONObject>>

    @GET("skills")
    fun getSkills(): Observable<Response<SkillModel>>

    @GET("tournaments/{tournamentId}")
    fun getTournamentDetail(@Path("tournamentId") idTournament: Int?): Observable<Response<TournamentDataModel>>
}