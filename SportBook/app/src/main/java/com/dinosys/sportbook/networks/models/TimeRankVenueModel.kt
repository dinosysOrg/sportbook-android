package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName
import okhttp3.Headers

data class TimeVenue(
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

class UpdateTimeRankModel(
        @SerializedName("preferred_time_slots") val timeBlocksModel: TimeBlocksModel?,
        @SerializedName("header") val header: Headers?
)

open class TimeBlocksModel(
        @SerializedName("monday") val monday: Array<String>?,
        @SerializedName("tuesday") val tuesday: Array<String>?,
        @SerializedName("wednesday") val wednesday: Array<String>?,
        @SerializedName("thursday") val thursday: Array<String>?,
        @SerializedName("friday") val friday: Array<String>?,
        @SerializedName("saturday") val saturday: Array<String>?,
        @SerializedName("sunday") val sunday: Array<String>?

)

data class RankVenueModel(
        @SerializedName("venue_name") val venue_name: String,
        @SerializedName("venue_distance_time") val venue_distance_time: String
)

