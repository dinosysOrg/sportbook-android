package com.dinosys.sportbook.features.mytournament.invitation

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.components.RxBus
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.extensions.popBackStack
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.OpponentTeamModel
import com.dinosys.sportbook.utils.DateUtil
import kotlinx.android.synthetic.main.fragment_invitaion.*
import java.lang.ref.WeakReference
import java.util.*


class InvitationFragment: BaseFragment(), InvitationListener {

    val INVITATION_TIME_PATTERN = "MMM, yyyy"

    var opponentTeamModel: OpponentTeamModel? = null

    var timeAdapter: TimeAdapter? = null

    init {
        RxBus.events(OpponentTeamModel::class.java)
                .subscribe { data -> opponentTeamModel = data }
                .addDisposableTo(this)
    }

    override fun inflateFromLayout(): Int = R.layout.fragment_invitaion

    override fun initViews() {
        super.initViews()
        tvOpponentName.text = opponentTeamModel?.teamName
        setUpInvitationUIViews()
        setUpInvitationList()
    }

    override fun initListeners() {
        super.initListeners()
        toolbar.setOnBackClickListener {
            fragmentManager.popBackStack(numberOfFragment = 1)
        }
    }

    private fun setUpInvitationUIViews() {
        val calendar = Calendar.getInstance()
        val invitationTimeHeader = DateUtil.convertDateToString(calendar.time, INVITATION_TIME_PATTERN)
        tvInvitaionDate.text = invitationTimeHeader

        val arrayDay = arrayListOf<Int>()
        val lastDayOfCurrentMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (day in 1..lastDayOfCurrentMonth) {
            arrayDay.add(day)
        }
        timeAdapter = TimeAdapter(arrayDay, WeakReference(this))

        rvTime.layoutManager = LinearLayoutManager(rvTime.context, LinearLayoutManager.HORIZONTAL, false)
        rvTime.adapter = timeAdapter


        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        rvTime.scrollToPosition(currentDay - 1)
        onDaySelected(currentDay)
    }

    private fun setUpInvitationList() {
        val invitationAPI = InvitationViewModel()
        val listTitle = invitationAPI.getListVenue()
        val listDetails = invitationAPI.getInviationData(appContext!!)

        val adapter = InvitationExpandableAdapter(appContext!!, listTitle, listDetails)
        lvInvitation.setAdapter(adapter)
    }


    override fun onDaySelected(day: Int) {
       timeAdapter?.currentDay = day
       timeAdapter?.notifyDataSetChanged()
    }

    companion object {
        val TAG = "InvitationFragment"
    }

}