package com.dinosys.sportbook.features.mytournament.venue

import android.content.Context
import android.util.Log
import com.dinosys.sportbook.networks.teams.TeamsAPI
import io.reactivex.Observable
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

import javax.inject.Inject

class VenueViewModel @Inject constructor(val teamsAPI: TeamsAPI) {

    fun updateTimeSlot(preferredTimeBlock: HashMap<String, Any>, venueRanking: ArrayList<Int>, teamId: Int?): Observable<Response<JSONObject>> {
        val hashMap = hashMapOf<String, Any>(
                "preferred_time_blocks" to preferredTimeBlock,
                "venue_ranking" to venueRanking
        )
        return teamsAPI.updateTimeSlots(hashMap, teamId!!)
    }

}