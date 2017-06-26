package com.dinosys.sportbook.features.mytournament.venue

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.RankVenueModel
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
            //val days = ArrayList<String>()
            items.add(TimeVenue(true, "", null));
            items.add(TimeVenue(false, "9am-12am", null));
            items.add(TimeVenue(false, "1pm-4pm", null));
            items.add(TimeVenue(false, "5pm-7pm", null));

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

}