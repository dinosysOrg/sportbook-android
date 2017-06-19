package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class TournamentModel(@SerializedName("_embedded") val embedded: TournamentEmbeddedModel?)

data class TournamentEmbeddedModel(@SerializedName("tournaments") val tournaments: ArrayList<TournamentDataModel>?)

data class TournamentDataModel(@SerializedName("id") val id: Int?,
                               @SerializedName("name") val name: String?,
                               @SerializedName("start_date") val startDate: String?,
                               @SerializedName("end_date") val endDate: String?)