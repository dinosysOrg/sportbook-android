package com.dinosys.sportbook.features.mytournament.invitation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dinosys.sportbook.R
import android.view.View
import kotlinx.android.synthetic.main.item_invitation_time.view.*
import java.lang.ref.WeakReference


class TimeAdapter(val days: List<Int>?, val invitationListener: WeakReference<InvitationListener>) : RecyclerView.Adapter<TimeViewHolder>() {

    var currentDay: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_invitation_time, parent, false)
        return TimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bindView(days!!.get(position), currentDay, invitationListener)
    }

    override fun getItemCount(): Int = this.days?.size ?: 0

}

class TimeViewHolder(temView: View): RecyclerView.ViewHolder(temView) {

    fun bindView(dateValue: Int, currentDay: Int?, invitationListener: WeakReference<InvitationListener>) = with(itemView) {
        tvInvationTime.text = "${dateValue}"
        if (currentDay == dateValue) {
            tvInvationTime.setBackgroundResource(R.drawable.rounded_textview)
        } else {
            tvInvationTime.setBackgroundResource(R.color.colorWhite)
        }
        tvInvationTime.setOnClickListener { invitationListener.get()?.onDaySelected(dateValue) }
    }

}

