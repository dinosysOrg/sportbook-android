package com.dinosys.sportbook.features.mytournament

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.ItemSpaceDecorator
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_tournament.*
import javax.inject.Inject

class MyTournamentFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament

    @Inject
    lateinit var tournamentApi: MyTournamentViewModel

    override fun initViews() {
        val heightDivider = resources.getDimensionPixelOffset(R.dimen.height_tournament_list_divider)
        val verticalSpacing = ItemSpaceDecorator(left = heightDivider, right = heightDivider,
                top = heightDivider, bottom = heightDivider)
        rvMyTournament.layoutManager = LinearLayoutManager(context)
        rvMyTournament.addItemDecoration(verticalSpacing)
    }

    override fun initData() {
        SportbookApp.tournamentComponent.inject(this)
        loadMyTournaments()
    }

    fun loadMyTournaments() {
        addDisposable(
                tournamentApi.getMyTournamentList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    e ->
                                    when (e.code()) {
                                        in 200..300 -> {
                                            val tournament = e.body()
                                            fillDataToAdapter(tournament)
                                        }
                                        else -> LogUtil.e(TAG, e.message())
                                    }
                                },
                                { t -> LogUtil.e(TAG, "Error: ${t.message}") }
                        )
        )
    }

    private fun fillDataToAdapter(tournaments: TournamentModel?) {
        val myTournamentData = tournaments?.embedded?.tournaments
        when (myTournamentData) {
            null -> {
                Log.e(TAG, "MyTournaments Data response is null!")
            }
            else -> rvMyTournament.adapter = MyTournamentAdapter(myTournamentData)
        }
    }

    companion object {
        val TAG = "MyTournamentFragment"
    }

}