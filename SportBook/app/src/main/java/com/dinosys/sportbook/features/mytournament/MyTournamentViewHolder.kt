package com.dinosys.sportbook.features.mytournament

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.extensions.loadFromUrl
import com.dinosys.sportbook.networks.models.MyTournamentModel
import kotlinx.android.synthetic.main.cardview_my_tournament.view.*

/**
 * Created by hanth on 07/06/2017.
 */
open class MyTournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(tournament: MyTournamentModel, position: Int) = with(itemView) {
        tvTournamentName.text = tournament.tournament_name.toString()
        tvTournamentTime.text = tournament.tournament_time.toString()
        tvRegisterStatus.text = tournament.register_status.toString()
        tvRegisterTime.text = tournament.register_time.toString()
        ivTournament.loadFromUrl(tournament.image_url)
    }
}