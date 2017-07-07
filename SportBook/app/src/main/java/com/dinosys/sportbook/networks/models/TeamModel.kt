package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class TeamModel(@SerializedName("id") val id: Int?,
                     @SerializedName("name") val name: String?,
                     @SerializedName("tournament_id") val tournamentId: Int?,
                     @SerializedName("status") val status: String?) {

    companion object {
        val STATUS_REGISTER = "registered"
        val STATUS_PAID = "paid"
    }

}

