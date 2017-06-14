package com.dinosys.sportbook.features.tournament

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.TournamentDataModel
import java.lang.ref.WeakReference

class TournamentAdapter(val tournaments: List<TournamentDataModel>?,
                    val tournamentReference: WeakReference<OnTournamentListener>
                ) : RecyclerView.Adapter<TournamentViewHolder>() {

    override fun onBindViewHolder(holder: TournamentViewHolder?, position: Int) {
            holder?.bindView(tournaments!![position], position, tournamentReference)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TournamentViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_tournament, parent, false)
        return TournamentViewHolder(view)
    }

    override fun getItemCount(): Int = this.tournaments?.size ?: 0

}