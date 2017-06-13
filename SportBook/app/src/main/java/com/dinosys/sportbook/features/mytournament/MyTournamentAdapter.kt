package com.dinosys.sportbook.features.mytournament

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.MyTournamentModel
import com.dinosys.sportbook.networks.models.TournamentModel

class MyTournamentAdapter(private val mFakeData: List<MyTournamentModel>, val activity: Activity) : RecyclerView.Adapter<MyTournamentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTournamentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_my_tournament, parent, false)
        val vh = MyTournamentViewHolder(view)
        return vh
    }

    override fun onBindViewHolder(holder: MyTournamentViewHolder, position: Int) {
        holder.bindView(mFakeData.get(position), position)
    }
    override fun getItemCount(): Int {

        return mFakeData.size
    }


}
