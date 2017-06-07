package com.dinosys.sportbook.features.mytournament

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.MyTournamentModel
import com.dinosys.sportbook.networks.models.TournamentModel
import kotlinx.android.synthetic.main.fragment_my_tournament.*

/**
 * Created by hanth on 06/06/2017.
 */

class MyTournamentFragment : BaseFragment(){

    override fun inflateFromLayout(): Int  = R.layout.fragment_my_tournament

    val mFakeData: MutableList<MyTournamentModel> = ArrayList()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in 1..5 ){
            val tournament: MyTournamentModel = MyTournamentModel(
                    "SampleTournmentName"+i,
                    "07/06/2017"+i,
                    "Registered"+i,
                    "07/06/2017"+i,"http://www.insidepoolmag.com/wp-content/uploads/worldstraight2.jpg")
            mFakeData.add(tournament)
        }

        val adapterMyTournament: MyTournamentAdapter = MyTournamentAdapter(mFakeData,activity)
        rvMyTournament.layoutManager = LinearLayoutManager(context)
        rvMyTournament.adapter = adapterMyTournament

    }

    companion object{
       val TAG = "MyTournamentFragment"
    }

}