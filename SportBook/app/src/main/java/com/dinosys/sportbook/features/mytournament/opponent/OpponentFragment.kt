package com.dinosys.sportbook.features.mytournament.opponent

<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager
=======
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
>>>>>>> 356658dec98f7bc40134199127dadd121e6683a1
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_tournament_opponent_list.*


class OpponentFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_opponent_list

<<<<<<< HEAD
    override fun initViews() {
=======
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    fun initViews() {
>>>>>>> 356658dec98f7bc40134199127dadd121e6683a1
        rvOpponentsList.adapter = OpponentAdapter(OpponentViewModel().sampleOpponents)
        rvOpponentsList.layoutManager = LinearLayoutManager(context)
    }

    override fun initListeners() {
        super.initListeners()
    }

    companion object{
        val TAG: String = "OpponentFragment"
    }
}