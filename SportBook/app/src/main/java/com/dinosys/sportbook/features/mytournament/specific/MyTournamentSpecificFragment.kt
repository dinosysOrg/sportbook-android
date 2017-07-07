package com.dinosys.sportbook.features.mytournament.detail

import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.dinosys.sportbook.R
import com.dinosys.sportbook.components.RxBus
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.TournamentDetailDataModel
import kotlinx.android.synthetic.main.fragment_my_tournament_specific.*

class MyTournamentSpecificFragment : BaseFragment() {

    var tournamentDetail: TournamentDetailDataModel? = null

    init {
        RxBus.events(TournamentDetailDataModel::class.java)
                .subscribe { data -> tournamentDetail = data }
                .addDisposableTo(this)
    }

    override fun inflateFromLayout(): Int = R.layout.fragment_my_tournament_specific

    override fun initViews() {
        lvTournamentSpecificMenu.adapter = ArrayAdapter<String>(context, R.layout.item_mytournament_menu_specific, resources.getStringArray(R.array.array_my_tournament_specific_menu)) as ListAdapter?
    }

    companion object {
        val TAG = "MyTournamentSpecific"
    }
}
