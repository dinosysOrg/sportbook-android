package com.dinosys.sportbook.features.mytournament.results

import com.dinosys.sportbook.R
import com.dinosys.sportbook.extensions.popBackStack
import com.dinosys.sportbook.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_mytournament_result.*

class ResultFragment: BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_result

    override fun initViews() {
        super.initViews()
        toolbarRound.setIconRightFromResource(R.string.icon_next)
    }

    override fun initData() {
        super.initData()
        val resultAdapter = ResultAdapter()
        lvResults.setAdapter(resultAdapter)
    }

    override fun initListeners() {
        super.initListeners()
        toolbarHeader.setOnBackClickListener {
            fragmentManager.popBackStack(1)
        }
    }

    companion object {
        val TAG = "MT_ResultFragment"
    }
}