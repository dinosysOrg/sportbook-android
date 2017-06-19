package com.dinosys.sportbook.features.mytournament.detail

import android.os.Bundle
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment

/**
 * Created by hanth on 14/06/2017.
 */
class MyTournamentDetailFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_registered

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        val TAG = "MyTournamentDetailFragment"
    }
}
