package com.dinosys.sportbook.features.mytournament.venue

import android.content.Context
import com.dinosys.sportbook.networks.models.TeamDataModel
import com.dinosys.sportbook.networks.models.TimeBlocksModel
import com.dinosys.sportbook.networks.models.UpdateTimeRankModel
import com.dinosys.sportbook.networks.teams.TeamsAPI
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class TimeRankVenueViewModel @Inject constructor(val teamsAPI: TeamsAPI) {



    fun updateTimeSlotsModel(context: Context?, preferred_time_blocks: TimeBlocksModel, venue_ranking: Array<Int>?, team_id: Int?)
            : Observable<Response<TeamDataModel>>{

        return teamsAPI.updateTimeSlots(preferred_time_blocks, venue_ranking, team_id)

    }

}