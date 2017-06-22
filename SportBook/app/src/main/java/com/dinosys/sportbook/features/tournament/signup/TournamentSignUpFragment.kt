package com.dinosys.sportbook.features.tournament.signup

import android.app.DatePickerDialog
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.components.PickerDialog
import com.dinosys.sportbook.configs.KEY_USER_SKILL_LEVEL
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.extensions.editTable
import com.dinosys.sportbook.extensions.popBackStack
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.tournament.overview.TournamentOverviewFragment
import com.dinosys.sportbook.managers.AuthenticationManager
import com.dinosys.sportbook.networks.models.SkillDataModel
import com.dinosys.sportbook.utils.DateUtil
import com.dinosys.sportbook.utils.DialogUtil
import com.dinosys.sportbook.utils.LogUtil
import com.dinosys.sportbook.utils.SharedPreferenceUtil
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tournament_signup.*
import kotlinx.android.synthetic.main.item_tournament_signup_personal.*
import kotlinx.android.synthetic.main.item_tournament_signup_skillset.*
import kotlinx.android.synthetic.main.item_tournament_signup_skillset_confirm.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import java.util.Calendar
import java.util.Collections
import javax.inject.Inject

class TournamentSignUpFragment : BaseFragment() {

    var signUpState: TournamentSignUpState? = null

    var idTournament: Int? = null

    var skills: ArrayList<SkillDataModel>? = null

    var cities: Array<String>? = null

    var districtes: JSONArray? = null

    var cityIndexSelected = UNSELECTED_CITY_INDEX

    var skillLevelCurrentSelected = 0

    @Inject
    lateinit var tournamentSignUpApi: TournamentSignUpViewModel

    override fun inflateFromLayout(): Int  = R.layout.fragment_tournament_signup

    override fun initViews() {
        idTournament = this.arguments.getInt(TournamentOverviewFragment.KEY_ID)
    }

    override fun initListeners() {
        btnSignUpPersonalContinue.setOnClickListener {
            if (!checkValidInputData()) {
               return@setOnClickListener
            }
            val skillHistoryLevelId = SharedPreferenceUtil.getInt(appContext!!, KEY_USER_SKILL_LEVEL, -1)
            when (skillHistoryLevelId) {
                -1 -> {
                    skillLevelCurrentSelected = 0
                    signUpState = TournamentSignUpState.SKILL_SET_PAGE
                    showLayoutByCurrentSignUpState()
                }
                else -> {
                    skillLevelCurrentSelected = skillHistoryLevelId
                    signUpState = TournamentSignUpState.SKILL_SET_CONFIRMED_PAGE
                    showLayoutByCurrentSignUpState()
                }
            }
        }

        btnSkillSetSubmit.setOnClickListener {
            SharedPreferenceUtil.saveInt(appContext!!, KEY_USER_SKILL_LEVEL, skillLevelCurrentSelected)
            signUpState = TournamentSignUpState.SKILL_SET_CONFIRMED_PAGE
            showLayoutByCurrentSignUpState()
        }

        RxView.clicks(btnSkillSetConfirmSubmit)
                .subscribeOn(AndroidSchedulers.mainThread())
                .switchMap {
                    val name = "${etFirstName.text.toString()}, ${etLastName.text.toString()}"
                    val birtday = etBirtday.text.toString()
                    val phoneNumber = etPhoneNumber.text.toString()
                    val address = "${etCity.text.toString()} ${etDistrict.text.toString()}"
                    val club = etClub.text.toString()
                    val skillId = skills!!.get(vpSkills.currentItem).id!!
                    tournamentSignUpApi.signUpTournament(idTournament!!, name, birtday, phoneNumber, address, club, skillId)
                            .subscribeOn(Schedulers.newThread())
                            .onErrorResumeNext { t: Throwable? -> onTournamentSignUpError(t) }
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onTournamentSignUpSuccessfully() },
                        { t -> onTournamentSignUpError(t) }
                )
                .addDisposableTo(this)

        etCity.setOnClickListener {
            val dialog = PickerDialog(etCity.context)
            dialog.title = getString(R.string.title_select_the_city)
            dialog.displayedValues = cities

            dialog.pickerDialogListener = object:PickerDialog.PickerDialogListener {
                override fun onItemSelected(index: Int, string: String) {
                    cityIndexSelected = index
                    etCity.text = string.editTable
                }
            }

            dialog.show()
        }

        etDistrict.setOnClickListener {
            val selectedCity = etCity.text.toString()

            if (selectedCity.isEmpty()) {
                return@setOnClickListener
            }

            val dialog = PickerDialog(etCity.context)
            dialog.title = getString(R.string.title_select_your_district)
            var jsonDistrictArray = districtes!!.getJSONObject(cityIndexSelected).getJSONArray("districts")
            dialog.displayedValues = Array(jsonDistrictArray.length(), { index-> jsonDistrictArray.getString(index)})

            dialog.pickerDialogListener = object:PickerDialog.PickerDialogListener {
                override fun onItemSelected(index: Int, string: String) {
                    etDistrict.text = string.editTable
                }
            }

            dialog.show()
        }

        etBirtday.setOnClickListener {
            val birtdaySetListener = OnBirtdayDateSetListener(etBirtday, DateUtil.SHORT_PATTERN)
            val calendar = Calendar.getInstance()
            DatePickerDialog(etBirtday.context, birtdaySetListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()

        }

        vpSkills.addOnPageChangeListener(object:ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                tvSkillLevelSelected.text = skills!!.get(position).name
                skillLevelCurrentSelected = skills!!.get(position).id!!
            }
        })
    }

    override fun initData() {
        SportbookApp.tournamentComponent.inject(this)
        signUpState = TournamentSignUpState.PERSONAL_PAGE

        val user = AuthenticationManager.getUser(appContext!!)
        etEmail.text = user?.data?.email?.editTable
        loadCities()
        loadSkills()
        showLayoutByCurrentSignUpState()
    }

    fun onTournamentSignUpError(t: Throwable?): Observable<Response<JSONObject>> {
        LogUtil.e(TAG, "onTournamentSignUpError] ${t?.message}")
        return Observable.empty()
    }

    fun onTournamentSignUpSuccessfully() {
        LogUtil.e(TAG, "onTournamentSignUpSuccessfully]")
        signUpState = TournamentSignUpState.FINISH_PAGE
        showLayoutByCurrentSignUpState()
    }

    private fun loadCities() {
        tournamentSignUpApi.getCities(appContext!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe({
                    jsonArray ->
                    if (jsonArray != null) {
                        districtes = jsonArray
                        cities = Array(districtes!!.length(), { index -> districtes!!.getJSONObject(index).getString("name") })
                    }
                }, {
                    error -> LogUtil.e(TAG, "[loadCities] error=${error.message}")
                }).addDisposableTo(this)
    }

    private fun checkValidInputData(): Boolean {
        if (!isValidFirstName()) {
            return false
        }
        if (!isValidLastName()) {
            return false
        }
        if (!isValidPhoneNumber()) {
            return false
        }
        if (!isValidCity()) {
            return false
        }
        if (!isValidDistrict()) {
            return false
        }
        return true
    }

    private fun isValidFirstName(): Boolean {
        return isNotEmptyInput(ipFirstName, etFirstName, R.string.error_please_fill_in_required_information_text)
    }

    private fun isValidLastName(): Boolean {
       return isNotEmptyInput(ipLastName, etLastName, R.string.error_please_fill_in_required_information_text)
    }

    private fun isValidPhoneNumber(): Boolean {
        val isNotEmptyInput = isNotEmptyInput(ipPhoneNumber, etPhoneNumber, R.string.error_please_fill_in_required_information_text)
        if (!isNotEmptyInput) {
            return false
        }

        val phone = etPhoneNumber.text.toString()
        val isValidPhoneNumber = android.util.Patterns.PHONE.matcher(phone).matches()
        if (!isValidPhoneNumber) {
            setErrorForEditText(ipPhoneNumber, etPhoneNumber, R.string.error_phone_invalid_text)
            return false
        }
        ipPhoneNumber.isErrorEnabled = false
        return true
    }

    private fun isValidCity(): Boolean {
        return isNotEmptyInput(ipCity, etCity, R.string.error_please_fill_in_required_information_text)
    }

    private fun isValidDistrict(): Boolean {
        return isNotEmptyInput(ipDistrict, etDistrict, R.string.error_please_fill_in_required_information_text)
    }

    private fun isNotEmptyInput(inputLayout: TextInputLayout, editText: EditText, resStringText: Int): Boolean {
        val firstName = editText.text.toString()
        if (firstName.isEmpty()) {
            setErrorForEditText(inputLayout, editText, resStringText)
            return false
        }
        inputLayout.isErrorEnabled = false
        return true
    }

    private fun setErrorForEditText(inputLayout: TextInputLayout, editText: EditText, resStringText: Int) {
        inputLayout.error = getString(resStringText)
        editText.requestFocus()
    }

    private fun loadSkills() {
        tournamentSignUpApi.getSkills()
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
                }, { throwable ->  LogUtil.e(TAG, "Error loading skills: ${throwable.message}") })
                .addDisposableTo(this)
    }

    private fun renderSkillLayout(){
        if (skills == null) {
            LogUtil.e(TAG, "[renderSkillLayout] data is null")
            return
        }
        Collections.reverse(skills)
        val historySkillId = SharedPreferenceUtil.getInt(appContext!!, KEY_USER_SKILL_LEVEL, 0)
        tvSkillLevelSelected.text = skills!!.get(historySkillId).name
        val skillAdapter = SkillViewPagerAdapter(skills!!)
        vpSkills.adapter = skillAdapter
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
                fragmentManager.popBackStack(2)
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
        val UNSELECTED_CITY_INDEX = -1
    }

}