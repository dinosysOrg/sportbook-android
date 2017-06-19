package com.dinosys.sportbook.features.mytournament.timetable

import com.dinosys.sportbook.networks.models.TimeTableModel
import java.util.*

class TimeTableViewModel {

    val sampleList: ArrayList<Any>
        get() {
            val items = ArrayList<Any>()
            items.add("This week")
            for (i in 0..4) {
                items.add(TimeTableModel("Monday", "A1 match with Le Minh A", "@ABC Venue", "7:00pm - 9:00pm"))
            }
            return items
        }


}
