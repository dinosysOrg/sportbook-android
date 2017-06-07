package com.dinosys.sportbook.features.tournament

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.extensions.loadFromUrl
import com.dinosys.sportbook.networks.models.TournamentModel
import kotlinx.android.synthetic.main.item_tournament.view.*
import java.text.FieldPosition


class TournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(tournament: TournamentModel, position: Int)  = with(itemView) {
        ivTournamentFeatureImage.loadFromUrl(tournament.featureImageUrl)
        tvTournamentRegisterTime.text = tournament.registrationTime
        tvTournamentTime.text = tournament.tournamentTime
        tvTournamentFotterName.text = tournament.name
        tvTournamentHeaderName.text = tournament.name
        when(position) {
            0 -> llTournamentItemHeader.visibility = View.GONE
            else -> tvTournamentFotterName.visibility = View.GONE
        }
    }

}