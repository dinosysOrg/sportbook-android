package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName

/**
 * Created by hanth on 16/06/2017.
 */

data class TimeTableModel(@SerializedName("day") val day: String?,
                          @SerializedName("match_name") val match_name: String?,
                          @SerializedName("venue") val venue: String?,
                          @SerializedName("match_time") val match_time: String?)
