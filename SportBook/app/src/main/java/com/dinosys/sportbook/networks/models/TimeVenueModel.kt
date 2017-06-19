package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class TimeVenueModel(
        @SerializedName("isHeader") val isHeader: Boolean,
        @SerializedName("time_block") val time_block: String,
        @SerializedName("isDayOneAvailable") val isDayOneAvailable: Boolean,
        @SerializedName("isDayTwoAvailable") val isDayTwoAvailable: Boolean,
        @SerializedName("isDayThreeAvailable") val isDayThreeAvailable: Boolean,
        @SerializedName("isDayFourAvailable") val isDayFourAvailable: Boolean,
        @SerializedName("isDayFiveAvailable") val isDayFiveAvailable: Boolean,
        @SerializedName("isDaySixAvailable") val isDaySixAvailable: Boolean,
        @SerializedName("isDaySevenAvailable") val isDaySevenAvailable: Boolean
)

data class RankVenueModel(
        @SerializedName("venue_name") val venue_name: String,
        @SerializedName("venue_distance_time") val venue_distance_time: String
)
