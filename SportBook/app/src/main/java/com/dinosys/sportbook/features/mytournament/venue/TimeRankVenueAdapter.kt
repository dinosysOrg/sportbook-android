package com.dinosys.sportbook.features.mytournament.venue

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.RankVenueModel
import com.dinosys.sportbook.networks.models.TimeVenue

class TimeRankVenueAdapter(val timeRankVenues: List<Any>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val INPUT_TIME = 0
    private val RANKVENUE = 1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val view: View?
        when (viewType) {
            INPUT_TIME -> {
                view = LayoutInflater.from(parent?.context).inflate(R.layout.item_my_tournament_input_time, parent, false)
                return InputTimeViewHolder(view)
            }
            RANKVENUE -> {
                view = LayoutInflater.from(parent?.context).inflate(R.layout.item_my_tournament_rank_venue, parent, false)
                return RankVenueViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent?.context).inflate(R.layout.item_my_tournament_input_time, parent, false)
                return InputTimeViewHolder(view)
            }
        }

        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder?.itemViewType) {
            INPUT_TIME -> {
                val inputTimeViewHolder = holder as InputTimeViewHolder
                configureTimeVenueHolder(inputTimeViewHolder, position)
            }
            RANKVENUE -> {
                val rankVenueViewHolder = holder as RankVenueViewHolder
                configureRankVenueHolder(rankVenueViewHolder, position)
            }
            else -> {
                val defaultViewHolder = holder as InputTimeViewHolder
                configureDefaultHolder(defaultViewHolder, position)
            }
        }
    }

    private fun configureDefaultHolder(vh1: InputTimeViewHolder, position: Int) {
        val timevenue = timeRankVenues?.get(position) as TimeVenue
        if (timevenue != null) {
            vh1.bindView(timevenue, position)
        }
    }

    private fun configureTimeVenueHolder(vh1: InputTimeViewHolder, position: Int) {
        val timevenue = timeRankVenues?.get(position) as TimeVenue
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
        if (timeRankVenues?.get(position) is TimeVenue) {
            return INPUT_TIME
        } else if (timeRankVenues?.get(position) is RankVenueModel) {
            return RANKVENUE
        }
        return -1
    }

    override fun getItemCount(): Int = this.timeRankVenues?.size ?: 0

}