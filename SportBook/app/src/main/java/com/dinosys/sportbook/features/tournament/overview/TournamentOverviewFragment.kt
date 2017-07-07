package com.dinosys.sportbook.features.tournament.overview

import android.os.Bundle
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.BaseTabViewPagerAdapter
import com.dinosys.sportbook.components.RxBus
import com.dinosys.sportbook.exceptions.TournamentSignUpFailureException
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.extensions.popBackStack
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.tournament.signup.TournamentSignUpFragment
import com.dinosys.sportbook.managers.AuthenticationManager
import com.dinosys.sportbook.networks.models.*
import com.dinosys.sportbook.utils.DialogUtil
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tournament_overview.*
import retrofit2.Response
import javax.inject.Inject

class TournamentOverviewFragment : BaseFragment() {

    var idTournament:Int? = null

    var tournamentDataModel: TournamentDetailDataModel? = null

    init {
        RxBus.events(TournamentDetailDataModel::class.java)
                .subscribe{ data -> tournamentDataModel = data }
                .addDisposableTo(this)
    }

    override fun inflateFromLayout(): Int = R.layout.fragment_tournament_overview

    @Inject
    lateinit var tournamentApi: TournamentOverviewViewModel

    override fun initViews() {
        val bundle = this.arguments
        if (bundle == null) {
            return
        }
        if (bundle.containsKey(KEY_ID)) {
            idTournament = bundle.getInt(KEY_ID)
        }
    }

    override fun initData() {
        super.initData()
        SportbookApp.tournamentComponent.inject(this)
        loadTournamentDetail()
    }

    private fun loadTournamentDetail() {
        if (tournamentDataModel == null) {
            loadTournamentDetailById()
        } else {
            renderTournamentOverviewLayout(tournamentDataModel)
        }
    }

    private fun loadTournamentDetailById() {
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
                }).addDisposableTo(this)
    }

    private fun setSignUpButtonStatus() {
        if (tournamentDataModel == null) {
            return
        }
        val status = tournamentDataModel?.teams?.status

        btnSignUpTournament.visibility = View.VISIBLE

        if (status == null) {
            btnSignUpTournament.isEnabled = true
        }

        if (status == TeamModel.STATUS_REGISTER) {
            btnSignUpTournament.text = getString(R.string.payment_peding_text)
            btnSignUpTournament.isEnabled = false
        }

        if (status == TeamModel.STATUS_PAID) {
            btnSignUpTournament.text = getString(R.string.paid)
            btnSignUpTournament.isEnabled = false
        }
    }

    override fun initListeners() {
        btnSignUpTournament.setOnClickListener {
            if (!btnSignUpTournament.isEnabled) {
                return@setOnClickListener
            }
            val auth = AuthenticationManager.getUserInfo(appContext!!)
            if (auth!!.canSignUpTournament()) {
               doSignUpTournament(auth)
            } else {
                openTournamentSignUpScreen()
            }
        }
    }

    private fun doSignUpTournament(authDataModel: AuthDataModel) {
        tournamentApi.signUpTournament(idTournament = idTournament!!, authModel = authDataModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    when(res.code()) {
                        in 200..300  -> {
                            val tournamentDataResponse = res.body()
                            onTournamentSignUpSuccessfully(tournamentDataResponse)
                        }
                        else -> {
                            onTournamentSignUpError(TournamentSignUpFailureException(getString(R.string.error_tournament_sign_up_failure)))
                        }
                    }
                }, {
                    t: Throwable? -> onTournamentSignUpError(t)
                }).addDisposableTo(this)

    }

    private fun onTournamentSignUpSuccessfully(tournamentSignUpModel: TournamentSignUpModel?) {
        val statusRegister = tournamentSignUpModel?.team?.status
        if (statusRegister == null) {
            LogUtil.e(TAG, "[onTournamentSignUpSuccessfully] statusRegister = NULL")
            return
        }
        if (statusRegister == TeamModel.STATUS_REGISTER) {
            DialogUtil.showWarning(activity, getString(R.string.warning_title), getString(R.string.error_your_payment_is_not_actived))
            fragmentManager.popBackStack(1)
        }
    }

    private fun onTournamentSignUpError(t: Throwable?): Observable<Response<TournamentSignUpModel>> {
        LogUtil.e(TournamentSignUpFragment.TAG, "onTournamentSignUpError] ${t?.message}")
        return Observable.empty()
    }

    private fun openTournamentSignUpScreen() {
        val bundle = Bundle()
        bundle.putInt(TournamentSignUpFragment.KEY_ID, idTournament!!)
        fragmentManager.openScreenByTag(TournamentSignUpFragment.TAG, bundle = bundle)
    }

    private fun renderTournamentOverviewLayout(tournament: TournamentDetailDataModel?) {
        if (tournament == null) {
            LogUtil.e(TAG, "[renderTournamentOverviewLayout] tournament null")
            return
        }
        this.tournamentDataModel = tournament
        setSignUpButtonStatus()

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
