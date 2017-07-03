package com.dinosys.sportbook.features.mytournament.opponent

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.networks.models.OpponentTeamModel
import kotlinx.android.synthetic.main.item_my_tournament_opponent_list.view.*
import java.lang.ref.WeakReference

class OpponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(opponent: OpponentTeamModel, position: Int, opponentItemListener: WeakReference<OpponentItemListener>) = with(itemView) {
        tvName.text = opponent.teamName
        tvName.setOnClickListener {
            opponentItemListener.get()?.onOpponetItemSelected(opponentTeamModel = opponent)
        }
    }

}