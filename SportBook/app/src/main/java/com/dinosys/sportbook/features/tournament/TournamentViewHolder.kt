package com.dinosys.sportbook.features.tournament

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.networks.models.TournamentDataModel
import kotlinx.android.synthetic.main.item_tournament.view.*
import java.lang.ref.WeakReference


class TournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(tournament: TournamentDataModel, position: Int, tournamentReference: WeakReference<OnTournamentListener>) = with(itemView) {
        //ivTournamentFeatureImage.loadFromUrl(tournament.featureImageUrl)
        //tvTournamentRegisterTime.text = tournament.registrationTime
        itemView.setOnClickListener {
           tournamentReference.get()?.onTournamentClick(tournament)
        }
        tvTournamentRegisterTime.visibility = View.INVISIBLE
        tvTournamentTime.text = tournament.startDate
        tvTournamentFotterName.text = tournament.name
        tvTournamentHeaderName.text = tournament.name
        when (position) {
            0 -> {
                llTournamentItemHeader.visibility = View.GONE
                tvTournamentFotterName.visibility = View.VISIBLE
            }
            else -> {
                tvTournamentFotterName.visibility = View.GONE
                llTournamentItemHeader.visibility = View.VISIBLE
            }
        }
    }

}