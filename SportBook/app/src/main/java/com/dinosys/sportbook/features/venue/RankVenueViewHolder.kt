package com.dinosys.sportbook.features.venue

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ArrayAdapter
import com.dinosys.sportbook.networks.models.RankVenueModel
import kotlinx.android.synthetic.main.item_my_tournament_rank_venue.view.*

class RankVenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(rankVenueModel: RankVenueModel, position: Int) = with(itemView) {
        tvVenueName.text = rankVenueModel.venue_name
        tvVenueDistance.text = rankVenueModel.venue_distance_time
        spVenueRank.adapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,sampleRank)
    }

    val sampleRank: ArrayList<String>
        get() {
            val items = ArrayList<String>()
            items.add("Rank")
            items.add("1")
            items.add("2")
            items.add("3")
            items.add("4")
            return items;
        }

}