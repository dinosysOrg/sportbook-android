package com.dinosys.sportbook.features.tournament.signup

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RadioButton
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.extensions.remove
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.tournament.overview.TournamentOverviewFragment
import com.dinosys.sportbook.networks.models.SkillDataModel
import com.dinosys.sportbook.utils.DialogUtil
import com.dinosys.sportbook.utils.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tournament_signup.*
import kotlinx.android.synthetic.main.item_tournament_signup_personal.*
import kotlinx.android.synthetic.main.item_tournament_signup_skillset.*
import kotlinx.android.synthetic.main.item_tournament_signup_skillset_confirm.*
import javax.inject.Inject

class TournamentSignUpFragment : BaseFragment() {

    var signUpState: TournamentSignUpState? = null

    var idTournament: Int? = null

    var skills: ArrayList<SkillDataModel>? = null

    var skillValueSelected: String? = null

    @Inject
    lateinit var tournamentSignUpApi: TournamentSignUpViewModel

    override fun inflateFromLayout(): Int  = R.layout.fragment_tournament_signup

    override fun initViews() {
        idTournament = this.arguments.getInt(TournamentOverviewFragment.KEY_ID)
    }

    override fun initListeners() {
        btnSignUpPersonalContinue.setOnClickListener {
            signUpState = TournamentSignUpState.SKILL_SET_PAGE
            showLayoutByCurrentSignUpState()
        }
        btnSkillSetSubmit.setOnClickListener {
            when (rgSkills.checkedRadioButtonId) {
                -1 -> {
                    tvIndicateSkillLevelError.visibility = View.VISIBLE
                }
                else -> {
                    signUpState = TournamentSignUpState.SKILL_SET_CONFIRMED_PAGE
                    showLayoutByCurrentSignUpState()
                }
            }

        }
        btnSkillSetConfirmSubmit.setOnClickListener {
            signUpState = TournamentSignUpState.FINISH_PAGE
            showLayoutByCurrentSignUpState()
        }
        rgSkills.setOnCheckedChangeListener { group, checkedId ->
                val rbChecked = rgSkills.findViewById(checkedId) as RadioButton
                skillValueSelected = rbChecked.text.toString()
                tvSkillLevelSelected.text = skillValueSelected
                tvIndicateSkillLevelError.visibility = View.INVISIBLE
        }
    }

    override fun initData() {
        SportbookApp.tournamentComponent.inject(this)
        signUpState = TournamentSignUpState.PERSONAL_PAGE
        loadSkills()
        showLayoutByCurrentSignUpState()
    }

    private fun loadSkills() {
        addDisposable(tournamentSignUpApi.getSkills()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    when(res.code()) {
                        in 200..300 -> {
                            skills = res.body()?.embedded?.skills
                            renderSkillLayout()
                        }
                        else -> {
                            LogUtil.e(TAG, "Error loading skills: ${res.message()}")
                        }
                    }
                }, { throwable ->  LogUtil.e(TAG, "Error loading skills: ${throwable.message}") }))
    }

    private fun renderSkillLayout(){
        if (skills == null) {
            LogUtil.e(TAG, "[renderSkillLayout] data is null")
            return
        }
        skills!!.forEach {
            skill -> val rbSkill = RadioButton(rgSkills.context)
            rbSkill.text = skill.name
            rbSkill.setTextColor(Color.BLACK)
            rgSkills.addView(rbSkill)
            rbSkill.invalidate()
        }
    }

    private fun showLayoutByCurrentSignUpState() {
        when (signUpState) {
            TournamentSignUpState.PERSONAL_PAGE -> {
                showPersonalLayout()
            }
            TournamentSignUpState.SKILL_SET_PAGE -> {
                updateViewForNextStep()
                showSkillSetLayout()
            }
            TournamentSignUpState.SKILL_SET_CONFIRMED_PAGE -> {
                showSkillSetConfirmLayout()
            }
            TournamentSignUpState.FINISH_PAGE -> {
                DialogUtil.showWarning(activity, getString(R.string.warning_title), getString(R.string.error_your_payment_is_not_actived))
                fragmentManager.remove(this)
            }
        }
    }

    private fun updateViewForNextStep() {
        btnStepRight.setBackgroundResource(R.drawable.background_tab_selected)
        btnStepRight.setTextColor(R.color.colorTabBackgroundSelected)
        stepHorizontalLineRight.setBackgroundColor(ContextCompat.getColor(appContext, R.color.colorTabBackgroundSelected))
    }

    private fun showPersonalLayout() {
        llPersonalContainer.visibility = VISIBLE
        llSkillSetContainer.visibility = GONE
        llSkillSetConfirmContainer.visibility = GONE
    }

    private fun showSkillSetLayout() {
        llPersonalContainer.visibility = GONE
        llSkillSetContainer.visibility = VISIBLE
        llSkillSetConfirmContainer.visibility = GONE
    }

    private fun showSkillSetConfirmLayout() {
        llPersonalContainer.visibility = GONE
        llSkillSetContainer.visibility = GONE
        llSkillSetConfirmContainer.visibility = VISIBLE
    }

    companion object {
        val TAG = "TSignUpFragment"
        val KEY_ID = "id"
    }

}