package com.dinosys.sportbook.features.mytournament.timetable

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.TimeTableModel

class TimeTableAdapter(private val items: List<TimeTableModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            TimeTableModel.TIME_TABLE_ITEM_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_tournament_timetable, parent, false)
                return TimeTableViewHolder(view)
            }
            TimeTableModel.TIME_TABLE_HEADER_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_tournament_timetable_header, parent, false)
                return TimeTableViewHolderHeader(view)
            }
        }
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items.get(position).type) {
            TimeTableModel.TIME_TABLE_ITEM_TYPE -> {
                val viewHolder = holder as TimeTableViewHolder
                viewHolder.bindView(items.get(position), position)
            }
            TimeTableModel.TIME_TABLE_HEADER_TYPE -> {
                val viewHolder = holder as TimeTableViewHolderHeader
                viewHolder.bindView(items.get(position).header_name.toString(), position)
            }
        }
    }


    private fun configureViewHolder(vh1: TimeTableViewHolder, position: Int) {
        val timetale = items[position] as TimeTableModel
        vh1.bindView(timetale, position)
    }

    override fun getItemViewType(position: Int): Int = items.get(position).type ?: 0

    override fun getItemCount(): Int = this.items.size ?: 0
}
