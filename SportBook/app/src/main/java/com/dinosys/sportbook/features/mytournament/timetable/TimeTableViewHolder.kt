package com.dinosys.sportbook.features.mytournament.timetable

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.networks.models.TimeTableModel
import kotlinx.android.synthetic.main.item_my_tournament_timetable.view.*

class TimeTableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(timeTableModel: TimeTableModel, position: Int) = with(itemView){
        tvDay.text = timeTableModel.day
        tvName.text = timeTableModel.match_name
        tvTime.text = timeTableModel.match_time
        tvVenue.text = timeTableModel.venue
    }




}