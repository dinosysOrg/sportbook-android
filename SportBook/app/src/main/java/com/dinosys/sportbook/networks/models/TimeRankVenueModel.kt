package com.dinosys.sportbook.networks.models

class TimeVenue(
        val isHeader: Boolean?,
        val timeBlock: String?,
        val listBlockTimeRange: List<String>?
) {
    fun isAvailableBlockTime(blockTime: String): Boolean {
        if (listBlockTimeRange == null || listBlockTimeRange.isEmpty()) {
            return false
        }
        if (listBlockTimeRange.contains(blockTime)) {
            return true
        }
        return false
    }
}

data class RankVenueModel(
        val venue_name: String,
        val venue_distance_time: String
)

