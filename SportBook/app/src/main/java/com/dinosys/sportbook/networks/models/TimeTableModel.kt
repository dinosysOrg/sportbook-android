package com.dinosys.sportbook.networks.models

data class TimeTableModel(val header_name: String?, val day:String?, val match_name: String?, val venue: String?, val match_time: String?, val type: Int) {

    companion object {
        val TIME_TABLE_HEADER_TYPE = 0
        val TIME_TABLE_ITEM_TYPE = 1
    }

}
