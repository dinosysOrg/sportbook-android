package com.dinosys.sportbook.features.mytournament.opponent

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.RxBus
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.popBackStack
import com.dinosys.sportbook.extensions.throwable
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.OpponentListModel
import com.dinosys.sportbook.networks.models.TournamentDetailDataModel
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_my_tournament_opponent_list.*
import retrofit2.Response
import javax.inject.Inject

class OpponentFragment : BaseFragment() {

    var tournamentDetail: TournamentDetailDataModel? = null

    init {
        RxBus.events(TournamentDetailDataModel::class.java)
                .subscribe { data -> tournamentDetail = data }
                .addDisposableTo(this)
    }

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_opponent_list

    @Inject
    lateinit var tournamentApi: OpponentViewModel

    override fun initViews() {
        SportbookApp.tournamentComponent.inject(this)

        rvOpponentsList.layoutManager = LinearLayoutManager(context)
    }

    override fun initListeners() {
        super.initListeners()
        toolbar.setOnBackClickListener{
            fragmentManager.popBackStack(1)
        }
    }

    override fun initData() {
        super.initData()
        tournamentApi.getOpponentList(tournamentDetail!!.id!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> onLoadOpponentListSuccessfully(response)}, { t: Throwable? -> onLoadOpponentListFailure(t)})
                .addDisposableTo(this)
    }

    private fun onLoadOpponentListSuccessfully(response: Response<OpponentListModel>) {
        when (response.code()) {
            in 200..300 -> {
                val myGroup = response.body()?.myGroups?.get(0)
                if (response.body() == null) {
                    onLoadOpponentListFailure(NullPointerException("My group is null"))
                } else {
                    tvGroupName.text = myGroup?.groupName
                    rvOpponentsList.adapter = OpponentAdapter(myGroup!!.opponentTeams!!)
                }
            }
            else -> onLoadOpponentListFailure(response.message().throwable)
        }
    }

    private fun onLoadOpponentListFailure(t: Throwable?) {
        LogUtil.e(TAG, "[onLoadOpponentListFailure] error = ${t?.message}")
    }

    companion object{
        val TAG: String = "OpponentFragment"
        val KEY_ID: String ="idTournament"
    }
}