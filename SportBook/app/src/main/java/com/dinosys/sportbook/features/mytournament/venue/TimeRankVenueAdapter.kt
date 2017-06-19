package com.dinosys.sportbook.features.mytournament.venue

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.RankVenueModel
import com.dinosys.sportbook.networks.models.TimeVenueModel

class TimeRankVenueAdapter(val timeRankVenues: List<Any>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val TIMEVENUE = 0
    private val RANKVENUE = 1


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent?.context)
        when (viewType) {
            TIMEVENUE -> {
                val v1 = inflater.inflate(R.layout.item_my_tournament_time_venue, parent, false)
                viewHolder = TimeVenueViewHolder(v1)
            }
            RANKVENUE -> {
                val v2 = inflater.inflate(R.layout.item_my_tournament_rank_venue, parent, false)
                viewHolder = RankVenueViewHolder(v2)
            }
            else -> {
                val v = inflater.inflate(R.layout.item_my_tournament_time_venue, parent, false)
                viewHolder = TimeVenueViewHolder(v)
            }
        }

        return viewHolder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder?.itemViewType) {
            TIMEVENUE -> {
                val vh1 = holder as TimeVenueViewHolder
                configureTimeVenueHolder(vh1, position)
            }
            RANKVENUE -> {
                val vh2 = holder as RankVenueViewHolder
                configureRankVenueHolder(vh2, position)
            }
            else -> {
                val vh = holder as TimeVenueViewHolder
                configureDefaultHolder(vh, position)
            }
        }
    }

    private fun configureDefaultHolder(vh1: TimeVenueViewHolder, position: Int) {
        val timevenue = timeRankVenues?.get(position) as TimeVenueModel
        if (timevenue != null) {
            vh1.bindView(timevenue, position)
        }
    }

    private fun configureTimeVenueHolder(vh1: TimeVenueViewHolder, position: Int) {
        val timevenue = timeRankVenues?.get(position) as TimeVenueModel
        if (timevenue != null) {
            vh1.bindView(timevenue, position)
        }
    }

    private fun configureRankVenueHolder(vh2: RankVenueViewHolder, position: Int) {
        val timevenue = timeRankVenues?.get(position) as RankVenueModel
        if (timevenue != null) {
            vh2.bindView(timevenue, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (timeRankVenues?.get(position) is TimeVenueModel) {
            return TIMEVENUE
        } else if (timeRankVenues?.get(position) is RankVenueModel) {
            return RANKVENUE
        }
        return -1
    }

    override fun getItemCount(): Int = this.timeRankVenues?.size ?: 0


}