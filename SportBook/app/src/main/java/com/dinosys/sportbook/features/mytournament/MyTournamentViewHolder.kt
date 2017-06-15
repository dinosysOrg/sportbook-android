package com.dinosys.sportbook.features.mytournament

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.networks.models.TournamentDataModel
import kotlinx.android.synthetic.main.item_my_tournament.view.*

open class MyTournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(tournament: TournamentDataModel, position: Int) = with(itemView) {

        tvTournamentName.text = tournament.name
        tvTournamentTime.text = tournament.startDate
        //tvRegisterStatus.text = tournament.register_status.toString()
        //tvRegisterTime.text = tournament.register_time.toString()
        //ivTournament.loadFromUrl(tournament.image_url)
    }
}