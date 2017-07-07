package com.dinosys.sportbook.features.mytournament

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.features.tournament.OnTournamentListener
import com.dinosys.sportbook.networks.models.TournamentDataModel
import kotlinx.android.synthetic.main.item_my_tournament.view.*
import java.lang.ref.WeakReference

open class MyTournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(tournament: TournamentDataModel, position: Int, myTournamentRef: WeakReference<OnTournamentListener>) = with(itemView) {

        tvTournamentName.text = tournament.name
        tvTournamentTime.text = tournament.startDate
        //tvRegisterStatus.text = tournament.register_status.toString()
        tvRegisterTime.visibility = View.INVISIBLE
        //ivTournament.loadFromUrl(tournament.image_url)
        itemView.setOnClickListener {
            myTournamentRef.get()?.onTournamentClick(tournament)
        }

    }
}