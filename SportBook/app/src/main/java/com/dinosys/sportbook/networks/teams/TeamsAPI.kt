package com.dinosys.sportbook.networks.teams

import com.dinosys.sportbook.networks.models.TeamDataModel
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface TeamsAPI{

    @GET("teams/{id}/time_slots")
    fun getTimeSlots(): Observable<Response<TeamDataModel>>

    @FormUrlEncoded
    @PUT("teams/{team_id}")
    fun updateTimeSlots(@Field("preferred_time_blocks") preferred_time_blocks: JSONObject?,
                        @Field("venue_ranking") venue_ranking: Array<Int>?,
                        @Path("team_id") team_id: Int?): Observable<Response<TeamDataModel>>
}