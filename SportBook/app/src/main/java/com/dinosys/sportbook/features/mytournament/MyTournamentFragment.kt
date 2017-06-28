package com.dinosys.sportbook.features.mytournament

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.ItemSpaceDecorator
import com.dinosys.sportbook.components.RxBus
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.mytournament.detail.MyTournamentSpecificFragment
import com.dinosys.sportbook.features.tournament.OnTournamentListener
import com.dinosys.sportbook.features.tournament.overview.TournamentOverviewFragment
import com.dinosys.sportbook.networks.models.TeamModel
import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.models.TournamentDetailDataModel
import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_tournament.*
import java.lang.ref.WeakReference
import javax.inject.Inject

class MyTournamentFragment : BaseFragment(), OnTournamentListener {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament

    @Inject
    lateinit var tournamentApi: MyTournamentViewModel

    override fun initViews() {
        val heightDivider = resources.getDimensionPixelOffset(R.dimen.height_tournament_list_divider)
        val verticalSpacing = ItemSpaceDecorator(left = heightDivider, right = heightDivider, top = heightDivider, bottom = heightDivider)
        rvMyTournament.layoutManager = LinearLayoutManager(context)
        rvMyTournament.addItemDecoration(verticalSpacing)
    }

    override fun initData() {
        SportbookApp.tournamentComponent.inject(this)
        loadMyTournaments()
    }

    fun loadMyTournaments() {
        tournamentApi.getMyTournamentList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { e ->
                            when (e.code()) {
                                in 200..300 -> {
                                    val tournament = e.body()
                                    fillDataToAdapter(tournament)
                                }
                                else -> LogUtil.e(TAG, e.message())
                            }
                        },
                        { t -> LogUtil.e(TAG, "Error: ${t.message}") }
                ).addDisposableTo(this)
    }

    private fun fillDataToAdapter(tournaments: TournamentModel?) {
        val myTournamentData = tournaments?.embedded?.tournaments
        when (myTournamentData) {
            null -> {
                LogUtil.e(TAG, "MyTournaments Data response is null!")
            }
            else -> rvMyTournament.adapter = MyTournamentAdapter(myTournamentData, WeakReference(this))
        }
    }

    override fun onTournamentClick(tournament: TournamentDataModel?) {
        if (tournament == null) {
            return
        }
        loadTournamentDetail(tournament.id!!)
    }

    private fun loadTournamentDetail(idTournament: Int) {
        tournamentApi.getTournamentDetail(idTournament)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    when (response.code()) {
                        in 200..300 -> {
                            val tournament = response.body()
                            onLoadTournamentDetailSuccessfully(tournament)
                        }
                        else -> {
                            LogUtil.e(TAG, "[loadTournamentDetail] error=${response.errorBody()?.string()}")
                        }
                    }
                }, {
                    t ->
                    LogUtil.e(TAG, "[loadTournamentDetail] error=${t.message}")
                }).addDisposableTo(this)
    }

    private fun onLoadTournamentDetailSuccessfully(tournamentDetail: TournamentDetailDataModel?) {
        if (tournamentDetail == null) {
            LogUtil.e(TAG, "[onLoadTournamentDetailSuccessfully] tournamentDetail = null")
            return
        }
        val status = tournamentDetail.teams?.status
        when (status) {
            TeamModel.STATUS_PAID -> openSpecificScreen(tournamentDetail)
            TeamModel.STATUS_REGISTER -> openSpecificScreen(tournamentDetail)
            else -> LogUtil.e(TAG, "[onLoadTournamentDetailSuccessfully] status = ${status}")
        }
    }

    private fun openSpecificScreen(tournamentDetail: TournamentDetailDataModel) {
        fragmentManager.openScreenByTag(MyTournamentSpecificFragment.TAG)
        RxBus.publish(tournamentDetail)
    }

    private fun openOverviewScreen(tournamentDetail: TournamentDetailDataModel) {
        fragmentManager.openScreenByTag(TournamentOverviewFragment.TAG)
        RxBus.publish(tournamentDetail)
    }

    companion object {
        val TAG = "MyTournamentFragment"
    }

}