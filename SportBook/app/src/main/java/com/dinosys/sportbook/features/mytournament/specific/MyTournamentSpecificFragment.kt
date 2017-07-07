package com.dinosys.sportbook.features.mytournament.detail

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.dinosys.sportbook.R
import com.dinosys.sportbook.components.RxBus
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.mytournament.opponent.OpponentFragment
import com.dinosys.sportbook.features.mytournament.results.ResultFragment
import com.dinosys.sportbook.features.mytournament.timetable.TimeTableFragment
import com.dinosys.sportbook.features.mytournament.venue.VenueFragment
import com.dinosys.sportbook.features.tournament.overview.TournamentOverviewFragment
import com.dinosys.sportbook.networks.models.TournamentDetailDataModel
import kotlinx.android.synthetic.main.fragment_my_tournament_specific.*

class MyTournamentSpecificFragment : BaseFragment() {

    var tournamentDetail: TournamentDetailDataModel? = null

    init {
        RxBus.events(TournamentDetailDataModel::class.java)
                .subscribe { data -> tournamentDetail = data }
                .addDisposableTo(this)
    }

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_specific

    override fun initViews() {
        lvTournamentSpecificMenu.adapter = ArrayAdapter<String>(context, R.layout.item_mytournament_menu_specific, resources.getStringArray(R.array.array_my_tournament_specific_menu)) as ListAdapter?
    }

    override fun initListeners() {
        super.initListeners()
        lvTournamentSpecificMenu.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    fragmentManager.openScreenByTag(TournamentOverviewFragment.TAG)
                    RxBus.publish(tournamentDetail!!)
                }
                1 -> {
                    fragmentManager.openScreenByTag(VenueFragment.TAG)
                    RxBus.publish(tournamentDetail!!)
                }
                2 -> {
                    fragmentManager.openScreenByTag(TimeTableFragment.TAG)
                    RxBus.publish(tournamentDetail!!)
                }
                3 -> {
                    fragmentManager.openScreenByTag(OpponentFragment.TAG)
                    RxBus.publish(tournamentDetail!!)
                }
                4 -> {
                    fragmentManager.openScreenByTag(ResultFragment.TAG)
                }
            }
        }
    }

    companion object {
        val TAG = "MyTournamentSpecific"
    }
}
