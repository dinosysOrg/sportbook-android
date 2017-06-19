package com.dinosys.sportbook.features.mytournament.timetable

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.TimeTableModel

class TimeTableAdapter(private val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TIMETABLE = 0
    private val HEADER = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            TIMETABLE -> {
                val v1 = inflater.inflate(R.layout.item_my_tournament_timetable, parent, false)
                viewHolder = TimeTableViewHolder(v1)
            }
            HEADER -> {
                val v2 = inflater.inflate(R.layout.item_my_tournament_timetable_header, parent, false)
                viewHolder = TimeTableViewHolderHeader(v2)
            }
            else -> {
                val v = inflater.inflate(R.layout.item_my_tournament_timetable, parent, false)
                viewHolder = TimeTableViewHolder(v)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TIMETABLE -> {
                val vh1 = holder as TimeTableViewHolder
                configureViewHolder(vh1, position)
            }
            HEADER -> {
                val vh2 = holder as TimeTableViewHolderHeader
                configureViewHolderHeader(vh2, position)
            }
            else -> {
                val vh3 = holder as TimeTableViewHolderHeader
                configureDefaultViewHolder(vh3, position)
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
        vh2.bindView(items[position].toString(), position)
    }


    override fun getItemViewType(position: Int): Int {
        if (items[position] is TimeTableModel) {
            return TIMETABLE
        } else if (items[position] is String) {
            return HEADER
        }
        return -1
    }

    override fun getItemCount(): Int {
        return this.items.size
    }
}
