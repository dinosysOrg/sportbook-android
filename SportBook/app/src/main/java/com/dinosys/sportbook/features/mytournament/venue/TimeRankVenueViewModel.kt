package com.dinosys.sportbook.features.mytournament.venue

import com.dinosys.sportbook.networks.models.RankVenueModel
import com.dinosys.sportbook.networks.models.TimeVenueModel

class TimeRankVenueViewModel() {

    val sampleList: ArrayList<TimeVenueModel>
        get() {
            val items = ArrayList<TimeVenueModel>()
            items.add(TimeVenueModel(true, "", true, false, false, false, false, false, false))
            items.add(TimeVenueModel(false, "9:am-12:am", true, false, false, false, false, false, false))
            items.add(TimeVenueModel(false, "1:pm-4:pm", true, false, false, false, false, false, false))
            items.add(TimeVenueModel(false, "5:pm-9:pm", true, true, true, true, true, true, true))
            return items
        }

    val sampleListRank: ArrayList<RankVenueModel>
        get() {
            val items = ArrayList<RankVenueModel>()
            items.add(RankVenueModel("DEF Venue", "3km-10mins"))
            items.add(RankVenueModel("ABC Venue", "5km-25mins"))
            items.add(RankVenueModel("XYZ Venue", "10km-35mins"))
            items.add(RankVenueModel("HIJ Venue", "13km-40mins"))
            return items
        }

}