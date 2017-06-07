package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

/**
 * Created by hanth on 06/06/2017.
 */
data class MyTournamentModel(@SerializedName("tournament_name") var tournament_name: String?,
                             @SerializedName("tournament_time") var tournament_time: String?,
                             @SerializedName("register_status") var register_status: String?,
                             @SerializedName("register_time") var register_time: String?,
                             @SerializedName("image_url") var image_url: String?)