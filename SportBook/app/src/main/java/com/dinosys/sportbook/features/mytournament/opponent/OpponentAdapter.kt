package com.dinosys.sportbook.features.mytournament.opponent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.tournament.OnTournamentListener
import com.dinosys.sportbook.networks.models.OpponentTeamModel
import java.lang.ref.WeakReference

class OpponentAdapter(val items: List<OpponentTeamModel>, val opponentItemListener: WeakReference<OpponentItemListener>) : RecyclerView.Adapter<OpponentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OpponentViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_my_tournament_opponent_list, parent, false)
        return OpponentViewHolder(v)
    }

    override fun onBindViewHolder(holder: OpponentViewHolder?, position: Int) {
        holder?.bindView(items.get(position), position, opponentItemListener)
    }

    override fun getItemCount(): Int = items.size
}

