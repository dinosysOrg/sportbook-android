package com.dinosys.sportbook.features.mytournament.timetable

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.TimeTableModel

class TimeTableAdapter(private val items: List<TimeTableModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view: View

        when (viewType) {

            TimeTableModel.TIMETABLE_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_tournament_timetable, parent, false)
                return TimeTableViewHolder(view)
            }
            TimeTableModel.HEADER_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_tournament_timetable_header, parent, false)
                return TimeTableViewHolderHeader(view)
            }

        }
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items.get(position) != null) {
            when (items.get(position).getmType()) {
                TimeTableModel.TIMETABLE_TYPE -> {
                    val vh1 = holder as TimeTableViewHolder
                    configureViewHolder(vh1, position)
                }
                TimeTableModel.HEADER_TYPE -> {
                    val vh2 = holder as TimeTableViewHolderHeader
                    configureViewHolderHeader(vh2, position)
                }
                else -> {
                    val vh3 = holder as TimeTableViewHolderHeader
                    configureDefaultViewHolder(vh3, position)
                }
            }
        }
    }

    private fun configureDefaultViewHolder(vh: TimeTableViewHolderHeader, position: Int) {
        vh.bindView(items[position].toString(), position)
    }

    private fun configureViewHolder(vh1: TimeTableViewHolder, position: Int) {
        val timetale = items[position] as TimeTableModel
        if (timetale != null) {
            vh1.bindView(timetale, position)
        }
    }

    private fun configureViewHolderHeader(vh2: TimeTableViewHolderHeader, position: Int) {
        vh2.bindView(items?.get(position).header_name.toString(), position)
    }

    override fun getItemViewType(position: Int): Int = items?.get(position).getmType() ?: 0

    override fun getItemCount(): Int = this.items?.size ?: 0
}
