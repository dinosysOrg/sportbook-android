package com.dinosys.sportbook.features.tournament


import android.os.Bundle
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.components.VerticalSpaceDecorator
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.TournamentModel
import kotlinx.android.synthetic.main.fragment_tournament.*
import com.facebook.FacebookSdk.getApplicationContext
import android.support.v7.widget.LinearLayoutManager

class TournamentFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_tournament

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        val tournaments = TournamentViewModel().getTournamentList()
        val verticalSpacing = VerticalSpaceDecorator(resources.getDimensionPixelOffset(R.dimen.height_tournament_list_divider))
        rvTournament.layoutManager = LinearLayoutManager(getApplicationContext())
        rvTournament.addItemDecoration(verticalSpacing);
        rvTournament.adapter = TournamentAdapter(tournaments)
    }

    companion object {
        val TAG = "TournamentFragment"
    }
}
