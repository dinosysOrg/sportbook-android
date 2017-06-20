package com.dinosys.sportbook.features.mytournament.detail

import android.os.Bundle
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.mytournament.opponent.OpponentFragment
import com.dinosys.sportbook.features.mytournament.timetable.TimeTableFragment
import com.dinosys.sportbook.features.mytournament.venue.TimeRankVenueFragment
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_tournament_registered.*
import javax.inject.Inject

class TournamentDetailFragment : BaseFragment() {

    var idTournament: Int? = null

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_registered

    @Inject
    lateinit var tournamentApi: TournamentDetailViewModel

    override fun initViews() {

       // idTournament = this.arguments.getInt(KEY_ID)

    }

    override fun initListeners() {
        super.initListeners()

        val bundle = Bundle()
        cvTimeRankVenue.setOnClickListener {
            //bundle.putInt(TimeRankVenueFragment.KEY_ID, idTournament!!)
            fragmentManager.openScreenByTag(tag = TimeRankVenueFragment.TAG)
        }
        cvTimeTable.setOnClickListener {
            //bundle.putInt(TimeTableFragment.KEY_ID, idTournament!!)
            fragmentManager.openScreenByTag(tag = TimeTableFragment.TAG)
        }

        cvOpponentsList.setOnClickListener {
            //bundle.putInt(OpponentFragment.KEY_ID, idTournament!!)
            fragmentManager.openScreenByTag(tag = OpponentFragment.TAG)
        }
    }

    override fun initData() {
        SportbookApp.tournamentComponent.inject(this)
        //loadTournamentDetail()
    }

    private fun loadTournamentDetail() {
        addDisposable(
                tournamentApi.getTournamentById(1)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { e ->
                                    when (e.code()) {
                                        in 200..300 -> {
                                            val tournament = e.body()
                                            // fillData and setOnclick
                                        }
                                        else -> LogUtil.e(TAG, e.message())
                                    }

                                },
                                { t -> LogUtil.e(TAG, "Error: ${t.message}") })
        )
    }

    companion object {
        val TAG = "TournamentDetailFragment"
        val KEY_ID = "idTournament"
    }
}
