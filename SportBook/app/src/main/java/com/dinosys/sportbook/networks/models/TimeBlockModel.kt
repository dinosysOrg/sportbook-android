package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class TimeBlockModel (

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("preferred_time_blocks")
    val preferredTimeBlocks: PreferredTimeBlocks? = null,

    @SerializedName("venue_ranking")
    val venueRanking: List<Int>? = null
)

data class PreferredTimeBlocks (

    @SerializedName("monday")
    val monday: List<List<Int>>? = null,

    @SerializedName("tuesday")
    val tuesday: List<List<Int>>? = null,

    @SerializedName("wednesday")
    val wednesday: List<List<Int>>? = null,

    @SerializedName("thursday")
    val thursday: List<List<Int>>? = null,

    @SerializedName("friday")
    val firday: List<List<Int>>? = null,

    @SerializedName("saturday")
    val staturday: List<List<Int>>? = null,

    @SerializedName("sunday")
    val sunday: List<List<Int>>? = null
)
