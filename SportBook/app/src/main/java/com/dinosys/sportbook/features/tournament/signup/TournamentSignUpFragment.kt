package com.dinosys.sportbook.features.tournament.signup

import android.view.View.GONE
import android.view.View.VISIBLE
import com.dinosys.sportbook.R
import com.dinosys.sportbook.extensions.remove
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.utils.DialogUtil
import kotlinx.android.synthetic.main.fragment_tournament_signup.*
import kotlinx.android.synthetic.main.item_tournament_signup_personal.*
import kotlinx.android.synthetic.main.item_tournament_signup_skillset.*
import kotlinx.android.synthetic.main.item_tournament_signup_skillset_confirm.*

class TournamentSignUpFragment : BaseFragment() {

    var signUpState: TournamentSignUpState? = null

    override fun inflateFromLayout(): Int  = R.layout.fragment_tournament_signup

    override fun initListeners() {
        btnSignUpPersonalContinue.setOnClickListener {
            signUpState = TournamentSignUpState.SKILL_SET_PAGE
            showLayoutByCurrentSignUpState()
        }
        btnSkillSetSubmit.setOnClickListener {
            signUpState = TournamentSignUpState.SKILL_SET_CONFIRMED_PAGE
            showLayoutByCurrentSignUpState()
        }
        btnSkillSetConfirmSubmit.setOnClickListener {
            signUpState = TournamentSignUpState.FINISH_PAGE
            showLayoutByCurrentSignUpState()
        }
    }

    override fun initData() {
        signUpState = TournamentSignUpState.PERSONAL_PAGE
        showLayoutByCurrentSignUpState()
    }

    fun showLayoutByCurrentSignUpState() {
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

    fun updateViewForNextStep() {
        btnStepRight.setBackgroundResource(R.drawable.background_tab_selected)
        btnStepRight.setTextColor(R.color.colorTabBackgroundSelected)
        stepHorizontalLineRight.setBackgroundColor(R.color.colorTabBackgroundSelected)
    }

    fun showPersonalLayout() {
        llPersonalContainer.visibility = VISIBLE
        llSkillSetContainer.visibility = GONE
        llSkillSetConfirmContainer.visibility = GONE
    }

    fun showSkillSetLayout() {
        llPersonalContainer.visibility = GONE
        llSkillSetContainer.visibility = VISIBLE
        llSkillSetConfirmContainer.visibility = GONE
    }

    fun showSkillSetConfirmLayout() {
        llPersonalContainer.visibility = GONE
        llSkillSetContainer.visibility = GONE
        llSkillSetConfirmContainer.visibility = VISIBLE
    }

    companion object {
        val TAG = "TournamentSignUpFragment"
    }

}