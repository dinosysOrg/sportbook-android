package com.dinosys.sportbook.features.mytournament.timetable

import com.dinosys.sportbook.networks.models.TimeTableModel
import java.util.*

class TimeTableViewModel {

    val sampleList: ArrayList<TimeTableModel>
        get() {

            val items = ArrayList<TimeTableModel>()
            items.add(TimeTableModel("This week", null, null, null, null, TimeTableModel.TIME_TABLE_HEADER_TYPE))
            items.add(TimeTableModel(null, "Saturday", "A1 match with Le Minh A", "@ABC Venue", "7:00pm - 9:00pm", TimeTableModel.TIME_TABLE_ITEM_TYPE))
            items.add(TimeTableModel(null, "Saturday", "A1 match with Tran Minh B", "@ABC Venue", "6:00pm - 9:00pm", TimeTableModel.TIME_TABLE_ITEM_TYPE))
            items.add(TimeTableModel(null, "Friday", "A1 match with Pham van E", "@ABC Venue", "7:00pm - 7:00pm", TimeTableModel.TIME_TABLE_ITEM_TYPE))

            return items
        }
}
