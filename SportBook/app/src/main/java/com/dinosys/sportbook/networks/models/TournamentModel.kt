package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class TournamentModel(@SerializedName("_embedded") val embedded: TournamentEmbeddedModel?)

data class TournamentEmbeddedModel(@SerializedName("tournaments") val tournaments: ArrayList<TournamentDataModel>?)

data class TournamentDataModel(@SerializedName("id") val id: Int?,
                               @SerializedName("name") val name: String?,
                               @SerializedName("start_date") val startDate: String?,
                               @SerializedName("end_date") val endDate: String?,
                               @SerializedName("teams") val teamDataModel: ArrayList<TeamDataModel>?)

data class TeamDataModel(@SerializedName("id") val id: Int?,
                         @SerializedName("name") val name: String?,
                         @SerializedName("created_at") val created_at: String?,
                         @SerializedName("updated_at") val updated_at: String?,
                         @SerializedName("tournament_id") val tournament_id: Int?,
                         @SerializedName("status") val status: String?,
                         @SerializedName("venue_ranking") val venue_ranking: Array<Int>?)