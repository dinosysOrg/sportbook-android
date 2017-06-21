package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

data class OpponentModel(
        @SerializedName("name") val name: String?,
        @SerializedName("score") val score: String?,
        @SerializedName("result") val result: String?,
        @SerializedName("status") val status: String?,
        @SerializedName("img_profile") val img_profile: String?
)