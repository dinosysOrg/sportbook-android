package com.dinosys.sportbook.networks.models

class TimeTableModel(val header_name: String?, val day:String?, val match_name: String?, val venue: String?, val match_time: String?, private val mType: Int) {

    fun getmType(): Int {
        return mType
    }

    companion object {
        val HEADER_TYPE = 0
        val TIMETABLE_TYPE = 1
    }

}
