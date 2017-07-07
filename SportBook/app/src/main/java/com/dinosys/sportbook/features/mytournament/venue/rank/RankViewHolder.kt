package com.dinosys.sportbook.features.mytournament.venue.rank

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ArrayAdapter
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.mytournament.venue.model.RankVenueUIModel
import kotlinx.android.synthetic.main.item_my_tournament_rank_venue.view.*

class RankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(rankVenueUIModel: RankVenueUIModel?, position: Int) = with(itemView) {
        tvVenueName.text = rankVenueUIModel?.venueName
        tvVenueDistance.text = rankVenueUIModel?.venueDistanceTime
        spVenueRank.adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, itemView.resources.getStringArray(R.array.array_rank_venues))
    }

}