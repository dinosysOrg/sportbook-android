package com.dinosys.sportbook.features.mytournament

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.components.VerticalSpaceDecorator
import com.dinosys.sportbook.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_tournament.*

class MyTournamentFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    fun initListener() {

        val adapterMyTournament: MyTournamentAdapter = MyTournamentAdapter(MyTournamentViewModel().getMyTournamentList(), activity)
        rvMyTournament.layoutManager = LinearLayoutManager(context)
        rvMyTournament.addItemDecoration(VerticalSpaceDecorator(resources.getDimensionPixelOffset(R.dimen.height_tournament_list_divider)))
        rvMyTournament.adapter = adapterMyTournament

    }

    companion object {
        val TAG = "MyTournamentFragment"
    }

}