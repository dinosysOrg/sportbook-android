package com.dinosys.sportbook.features.tournament


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.ItemSpaceDecorator
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.mytournament.MyTournamentFragment
import com.dinosys.sportbook.features.tournament.overview.TournamentOverviewFragment
import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.models.TournamentModel
import com.dinosys.sportbook.utils.LogUtil
import com.facebook.FacebookSdk.getApplicationContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tournament.*
import java.lang.ref.WeakReference
import javax.inject.Inject

class TournamentFragment : BaseFragment(), OnTournamentListener {

    override fun inflateFromLayout(): Int = R.layout.fragment_tournament

    @Inject
    lateinit var tournamentApi: TournamentViewModel

    override fun initViews() {
        val heightDivider = resources.getDimensionPixelOffset(R.dimen.height_tournament_list_divider)
        val verticalSpacing = ItemSpaceDecorator(left = heightDivider, right = heightDivider,
                top = heightDivider, bottom = heightDivider)
        rvTournament.layoutManager = LinearLayoutManager(getApplicationContext())
        rvTournament.addItemDecoration(verticalSpacing);
    }

    override fun initData() {
        SportbookApp.tournamentComponent.inject(this)
        loadTournaments()
    }

    private fun loadTournaments() {
        addDisposable(
                tournamentApi.getTournaments()
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
                                { t -> LogUtil.e(TAG, "Error: ${t.message}") })
        )
    }

    private fun fillDataToAdapter(tournament: TournamentModel?) {
        val tournamentData = tournament?.embedded?.tournaments
        val myTournament = TournamentDataModel(ID_MY_TOURNAMENT, getString(R.string.title_mytournament))
        tournamentData?.add(0, myTournament)
        when (tournamentData) {
            null -> {
                LogUtil.e(TAG, "Tournament data response is null!")
            }
            else -> rvTournament.adapter = TournamentAdapter(tournamentData, WeakReference(this))
        }
    }

    override fun onTournamentClick(tournament: TournamentDataModel?) {
        if (tournament == null) {
            return
        }
        val idTournament = tournament.id!!
        when (idTournament) {
            ID_MY_TOURNAMENT -> fragmentManager.openScreenByTag(MyTournamentFragment.TAG)
            else -> openTournamentOverview(idTournament)
        }
     }

    private fun openTournamentOverview(tournamentId: Int) {
        val bundle = Bundle()
        bundle.putInt(TournamentOverviewFragment.KEY_ID, tournamentId)
        fragmentManager.openScreenByTag(tag = TournamentOverviewFragment.TAG, bundle = bundle )
    }

    companion object {
        val TAG = "TournamentFragment"
        val ID_MY_TOURNAMENT = -1
    }
}
