package com.dinosys.sportbook.features.tournament


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.ItemSpaceDecorator
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.tournament.overview.TournamentOverviewFragment
import com.dinosys.sportbook.managers.AuthenticationManager
import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.models.TournamentModel
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
        val auth = AuthenticationManager.getUser(appContext!!)
        addDisposable(
                tournamentApi.getTournaments(appContext, auth)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { e ->
                                    when (e.code()) {
                                        in 200..300 -> {
                                            val tournament = e.body()
                                            fillDataToAdapter(tournament)
                                        }
                                        else -> Log.e(TAG, e.message())
                                    }

                                },
                                { t -> Log.e(TAG, "Error: ${t.message}") })
        )
    }

    private fun fillDataToAdapter(tournament: TournamentModel?) {
        val tournamentData = tournament?.embedded?.tournaments
        when (tournamentData) {
            null -> {
                Log.e(TAG, "Tournament data response is null!")
            }
            else -> rvTournament.adapter = TournamentAdapter(tournamentData, WeakReference(this))
        }
    }

    override fun onTournamentClick(tournament: TournamentDataModel?) {
        if (tournament == null) {
            return
        }
        val bundle = Bundle()
        bundle.putInt(TournamentOverviewFragment.KEY_ID, tournament.id!!)
        fragmentManager.openScreenByTag(tag = TournamentOverviewFragment.TAG, bundle = bundle )
    }

    companion object {
        val TAG = "TournamentFragment"
    }
}
