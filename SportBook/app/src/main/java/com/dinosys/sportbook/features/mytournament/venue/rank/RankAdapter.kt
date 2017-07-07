package com.dinosys.sportbook.features.mytournament.venue.rank

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.mytournament.venue.model.RankVenueUIModel

class RankAdapter(val rankVenues: List<RankVenueUIModel>?) : RecyclerView.Adapter<RankViewHolder>() {

    override fun onBindViewHolder(holder: RankViewHolder?, position: Int) {
        holder?.bindView(rankVenues?.get(position), position)
    }

    override fun getItemCount(): Int = this.rankVenues?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RankViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_my_tournament_rank_venue, parent, false)
        return RankViewHolder(view)
    }

}