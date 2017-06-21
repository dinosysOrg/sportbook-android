package com.dinosys.sportbook.features.mytournament

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.ItemSpaceDecorator
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.mytournament.detail.TournamentDetailFragment
import com.dinosys.sportbook.features.tournament.OnTournamentListener
import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.networks.models.TournamentModel
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
                                        else -> Log.e(TAG, e.message())
                                    }
                                },
                                { t -> Log.e(TAG, "Error: ${t.message}") }
                        )
        )
    }

    private fun fillDataToAdapter(tournaments: TournamentModel?) {
        val myTournamentData = tournaments?.embedded?.tournaments
        when (myTournamentData) {
            null -> {
                Log.e(TAG, "MyTournaments Data response is null!")
            }
            else -> rvMyTournament.adapter = MyTournamentAdapter(myTournamentData, WeakReference(this))
        }
    }

    override fun onTournamentClick(tournament: TournamentDataModel?) {
        if (tournament == null) {
            return
        }
        val bundle = Bundle()
        bundle.putInt(TournamentDetailFragment.KEY_ID, tournament.id!!)
        fragmentManager.openScreenByTag(tag = TournamentDetailFragment.TAG, bundle = bundle)
    }

    companion object {
        val TAG = "MyTournamentFragment"
    }
}