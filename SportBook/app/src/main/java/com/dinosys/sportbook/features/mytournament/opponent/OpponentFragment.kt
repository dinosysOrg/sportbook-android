package com.dinosys.sportbook.features.mytournament.opponent

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_tournament_opponent_list.*

class OpponentFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_opponent_list

    override fun initViews() {
        rvOpponentsList.adapter = OpponentAdapter(OpponentViewModel().sampleOpponents)
        rvOpponentsList.layoutManager = LinearLayoutManager(context)
    }

    override fun initListeners() {
        super.initListeners()
    }

    companion object{
        val TAG: String = "OpponentFragment"
        val KEY_ID: String ="idTournament"
    }
}