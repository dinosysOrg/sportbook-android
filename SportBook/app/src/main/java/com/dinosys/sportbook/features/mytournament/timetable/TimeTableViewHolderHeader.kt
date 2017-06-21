package com.dinosys.sportbook.features.mytournament.timetable

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_my_tournament_timetable_header.view.*

class TimeTableViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(headerName: String, position: Int) = with(itemView) {
        tvHeaderName.text = headerName;
    }
}