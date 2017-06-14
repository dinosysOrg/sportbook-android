package com.dinosys.sportbook.features.tournament.overview

import android.os.Bundle
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment

class TournamentOverviewFragment : BaseFragment() {

    var idTournament:Int? = null

    override fun inflateFromLayout(): Int = R.layout.fragment_tournament_overview

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initViews() {
        idTournament = this.arguments.getInt(KEY_ID)
    }

    companion object {
        val TAG = "TournamentOverviewFragment"
        val KEY_ID = "id"
    }
}
