package com.dinosys.sportbook.features.tournament.overview

import android.util.Log
import com.dinosys.sportbook.R
import com.dinosys.sportbook.extensions.loadHtmlToText
import com.dinosys.sportbook.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_tournament_overview_tab_content.*

class TournamentOverviewTabContentFragment : BaseFragment() {

    var content: String? = null

    override fun inflateFromLayout(): Int  = R.layout.fragment_tournament_overview_tab_content

    override fun initData() {
        tvTournamentOverviewTabContent.loadHtmlToText(content)
    }

    companion object {
        val TAG = "OverviewTabContent"
    }
}