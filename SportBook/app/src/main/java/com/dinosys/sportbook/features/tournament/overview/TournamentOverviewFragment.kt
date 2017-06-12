package com.dinosys.sportbook.features.tournament.overview

import android.os.Bundle
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment

class TournamentOverviewFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_tournament_overview

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        val TAG = "TournamentOverviewFragment"
    }
}
