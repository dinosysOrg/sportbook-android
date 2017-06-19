package com.dinosys.sportbook.features.tournament.overview

import android.os.Bundle
import com.dinosys.sportbook.R
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.tournament.signup.TournamentSignUpFragment
import kotlinx.android.synthetic.main.fragment_tournament_overview.*

class TournamentOverviewFragment : BaseFragment() {

    var idTournament:Int? = null

    override fun inflateFromLayout(): Int = R.layout.fragment_tournament_overview

    override fun initViews() {
        idTournament = this.arguments.getInt(KEY_ID)
    }

    override fun initListeners() {
        btnSignUpTournament.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(TournamentSignUpFragment.KEY_ID, idTournament!!)
            fragmentManager.openScreenByTag(TournamentSignUpFragment.TAG, bundle = bundle)
        }
    }

    companion object {
        val TAG = "TournamentOverviewFragment"
        val KEY_ID = "id"
    }
}
