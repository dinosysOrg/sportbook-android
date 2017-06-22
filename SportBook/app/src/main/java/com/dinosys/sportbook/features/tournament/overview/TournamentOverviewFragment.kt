package com.dinosys.sportbook.features.tournament.overview

import android.os.Bundle
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.BaseTabViewPagerAdapter
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.tournament.signup.TournamentSignUpFragment
import com.dinosys.sportbook.networks.models.TournamentDataModel
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tournament_overview.*
import javax.inject.Inject

class TournamentOverviewFragment : BaseFragment() {

    var idTournament:Int? = null

    override fun inflateFromLayout(): Int = R.layout.fragment_tournament_overview

    @Inject
    lateinit var tournamentApi: TournamentOverviewViewModel

    override fun initViews() {
        idTournament = this.arguments.getInt(KEY_ID)
    }

    override fun initData() {
        super.initData()
        SportbookApp.tournamentComponent.inject(this)

        if (idTournament == null) {
            return
        }
        loadTournamentDetail()
    }

    private fun loadTournamentDetail() {
        tournamentApi.getTournamentDetail(idTournament!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    when(response.code()) {
                        in 200..300 -> {
                            val tournament = response.body()
                            renderTournamentOverviewLayout(tournament)
                        }
                        else -> {
                           LogUtil.e(TAG, "[loadTournamentDetail] error=${response.errorBody()?.string()}")
                        }
                    }
                }, {
                    t -> LogUtil.e(TAG, "[loadTournamentDetail] error=${t.message}")
                })
    }

    override fun initListeners() {
        btnSignUpTournament.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(TournamentSignUpFragment.KEY_ID, idTournament!!)
            fragmentManager.openScreenByTag(TournamentSignUpFragment.TAG, bundle = bundle)
        }
    }

    private fun renderTournamentOverviewLayout(tournament: TournamentDataModel?) {
        if (tournament == null) {
            LogUtil.e(TAG, "[renderTournamentOverviewLayout] tournament null")
            return
        }
        val baseViewPager = BaseTabViewPagerAdapter(manager = childFragmentManager)

        val competitionFee = tournament.competitionFee
        val competitionMode = tournament.competitionMode
        val competitionSchedule = tournament.competitionSchedule

        if (!competitionFee.isNullOrEmpty()) {
            val title = getString(R.string.title_competition_fee)
            addItemToAdapter(baseViewPager, title, competitionFee!!)
        }

        if (!competitionMode.isNullOrEmpty()) {
            val title = getString(R.string.title_competition_mode)
            addItemToAdapter(baseViewPager, title, competitionMode!!)
        }

        if (!competitionSchedule.isNullOrEmpty()) {
            val title = getString(R.string.title_competition_schedule)
            addItemToAdapter(baseViewPager, title, competitionSchedule!!)
        }

        viewPager.adapter = baseViewPager
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun addItemToAdapter(baseViewPager: BaseTabViewPagerAdapter, title: String, content: String) {
        val fragmentContent = TournamentOverviewTabContentFragment()
        fragmentContent.content = content
        baseViewPager.addFragments(fragmentContent, title)
    }

    companion object {
        val TAG = "TournamentOverviewFragment"
        val KEY_ID = "id"
    }
}
