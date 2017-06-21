package com.dinosys.sportbook.features.mytournament.timetable

import android.support.v7.widget.LinearLayoutManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_tournament_timetable.*

class TimeTableFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_timetable

    override fun initViews() {
        rvTimeTable.adapter = TimeTableAdapter(TimeTableViewModel().sampleList)
        rvTimeTable.layoutManager = LinearLayoutManager(context)
    }

    override fun initListeners() {
        super.initListeners()
    }

    companion object{
        val TAG: String =  "TimeTableFragment"
        val KEY_ID: String = "idTournament"
    }
}