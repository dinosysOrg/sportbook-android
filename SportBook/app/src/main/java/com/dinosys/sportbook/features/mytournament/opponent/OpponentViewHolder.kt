package com.dinosys.sportbook.features.mytournament.opponent

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.extensions.loadFromUrl
import com.dinosys.sportbook.networks.models.OpponentModel
import kotlinx.android.synthetic.main.item_my_tournament_opponent_list.view.*

class OpponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(opponentModel: OpponentModel, position: Int) = with(itemView) {
        tvName.text = opponentModel.name
        tvScore.text = opponentModel.score
        tvStatus.text = opponentModel.status
        tvResult.text = opponentModel.result
        ivImgProfile.loadFromUrl(opponentModel.img_profile)
    }
}