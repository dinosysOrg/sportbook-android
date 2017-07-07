package com.dinosys.sportbook.networks.teams

import com.dinosys.sportbook.networks.models.TimeBlockModel
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface TeamsAPI {

    @GET("teams/{id}/time_slots")
    fun getTimeSlots(@Path("id") teamId: Int): Observable<Response<JSONObject>>

    @PUT("teams/{team_id}")
    fun updateTimeSlots(@Body body: HashMap<String, Any>, @Path("team_id") teamId: Int): Observable<Response<JSONObject>>

    @GET("teams/{team_id}/time_blocks")
    fun getTimeBlocks(@Path("team_id") teamId: Int?): Observable<Response<TimeBlockModel>>

}