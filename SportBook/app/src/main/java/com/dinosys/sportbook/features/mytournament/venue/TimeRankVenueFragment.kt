package com.dinosys.sportbook.features.mytournament.venue

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.RankVenueModel
import com.dinosys.sportbook.networks.models.TimeBlocksModel
import com.dinosys.sportbook.networks.models.TimeVenue
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_my_tournament_time_rank_venue_change.*
import javax.inject.Inject

class TimeRankVenueFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_time_rank_venue_change

    @Inject
    lateinit var timerankvenueApi: TimeRankVenueViewModel

    override fun initViews() {
        rvTimeVenue.adapter = TimeRankVenueAdapter(sampleList)
        rvTimeVenue.layoutManager = LinearLayoutManager(context)

        rvRankVenue.adapter = TimeRankVenueAdapter(sampleListRank)
        rvRankVenue.layoutManager = LinearLayoutManager(context)
    }

    override fun initData() {
        SportbookApp.teamComponent.inject(this)
    }

    override fun initListeners() {
        super.initListeners()
        val btnTimeSlotsDisposable = RxView.clicks(btnUpdateTimeVenue)
                .subscribeOn(AndroidSchedulers.mainThread())
                //.switchMap {  }

    }

    companion object {
        val TAG: String = "TimeRankVenueFragment"
        val KEY_ID: String = "idTournament"
    }

    val sampleList: ArrayList<TimeVenue>
        get() {
            val items = ArrayList<TimeVenue>()
            items.add(TimeVenue(true, "", true, false, false, false, false, false, false))
            items.add(TimeVenue(false, "9:am-12:am", true, false, false, false, false, false, false))
            items.add(TimeVenue(false, "1:pm-4:pm", true, false, false, false, false, false, false))
            items.add(TimeVenue(false, "5:pm-9:pm", true, true, true, true, true, true, true))
            return items
        }

    val sampleListRank: ArrayList<RankVenueModel>
        get() {
            val items = ArrayList<RankVenueModel>()
            items.add(RankVenueModel("DEF Venue", "3km-10mins"))
            items.add(RankVenueModel("ABC Venue", "5km-25mins"))
            items.add(RankVenueModel("XYZ Venue", "10km-35mins"))
            items.add(RankVenueModel("HIJ Venue", "13km-40mins"))
            return items
        }


    val sampleListBlocks: ArrayList<TimeBlocksModel>
        get() {
            val items = ArrayList<TimeBlocksModel>()
            items.add(TimeBlocksModel(null,null,null,null,null,null,null))
            items.add(TimeBlocksModel(null,null,null,null,null,null,null))
            items.add(TimeBlocksModel(null,null,null,null,null,null,null))
            items.add(TimeBlocksModel(null,null,null,null,null,null,null))

            return items
        }
}