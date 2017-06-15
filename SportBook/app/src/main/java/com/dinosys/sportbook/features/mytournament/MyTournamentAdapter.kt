package com.dinosys.sportbook.features.mytournament

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.TournamentDataModel

class MyTournamentAdapter(val myTournaments: List<TournamentDataModel>?) : RecyclerView.Adapter<MyTournamentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTournamentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_tournament, parent, false)
        val vh = MyTournamentViewHolder(view)
        return vh
    }

    override fun onBindViewHolder(holder: MyTournamentViewHolder, position: Int) {
        holder.bindView(myTournaments!![position], position)
    }
    override fun getItemCount(): Int = this.myTournaments?.size ?: 0


}
