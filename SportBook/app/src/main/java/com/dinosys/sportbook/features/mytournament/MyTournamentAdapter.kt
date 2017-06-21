package com.dinosys.sportbook.features.mytournament

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.tournament.OnTournamentListener
import com.dinosys.sportbook.networks.models.TournamentDataModel
import java.lang.ref.WeakReference

class MyTournamentAdapter(val myTournaments: List<TournamentDataModel>?,
                          val tournamentReference: WeakReference<OnTournamentListener>) : RecyclerView.Adapter<MyTournamentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTournamentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_tournament, parent, false)
        return MyTournamentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyTournamentViewHolder, position: Int) {
        holder.bindView(myTournaments!!.get(position), position, tournamentReference)
    }

    override fun getItemCount(): Int = this.myTournaments?.size ?: 0
}
