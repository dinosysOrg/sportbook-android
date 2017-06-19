package com.dinosys.sportbook.features.venue

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_tournament_time_rank_venue_change.*

class TimeRankVenueFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_time_rank_venue_change



    override fun initViews() {
        rvTimeVenue.adapter = TimeRankVenueAdapter(TimeRankVenueViewModel().sampleList)
        rvTimeVenue.layoutManager = LinearLayoutManager(context)

        rvRankVenue.adapter = TimeRankVenueAdapter(TimeRankVenueViewModel().sampleListRank)
        rvRankVenue.layoutManager = LinearLayoutManager(context)
    }

    override fun initListeners() {
        super.initListeners()

    }

    companion object{
        val TAG: String = "TimeRankVenueFragment"
    }


}