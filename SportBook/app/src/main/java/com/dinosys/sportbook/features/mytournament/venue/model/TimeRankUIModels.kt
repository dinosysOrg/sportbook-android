package com.dinosys.sportbook.features.mytournament.venue.model

class TimeSlotUIModel(val isHeader: Boolean = false, val timeBlock: String = "", var blockTimeRangeList: ArrayList<String>? = null) {

    fun isAvailableBlockTime(blockTime: String): Boolean {
        if (blockTimeRangeList == null || blockTimeRangeList!!.isEmpty()) {
            return false
        }
        if (blockTimeRangeList!!.contains(blockTime)) {
            return true
        }
        return false
    }
}

class RankVenueUIModel(val isHeader: Boolean?, val venueName: String, val venueDistanceTime: String)

