package com.dinosys.sportbook.features.mytournament.venue.input

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.mytournament.venue.model.TimeSlotUIModel
import kotlinx.android.synthetic.main.item_my_tournament_input_timeslot.view.*
import java.lang.ref.WeakReference

class InputTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(timeSlot: TimeSlotUIModel?, position: Int, onTimeBlockListener: WeakReference<OnTimeBlocksListener>) = with(itemView) {

        val arrayDays = itemView.resources.getStringArray(R.array.array_time_range_days)
        if (timeSlot?.isHeader!!) {
            tvTimeBlockLeft.visibility = View.INVISIBLE
            arrayDays.forEachIndexed { index, headerText -> renderTimeRangeHeaderUI(findTextViewByPosition(index), headerText) }
        } else {
            tvTimeBlockLeft.visibility = View.VISIBLE
            tvTimeBlockLeft.text = timeSlot.timeBlock
            arrayDays.forEachIndexed { index, headerText -> renderTimeRangeItemUI(findTextViewByPosition(index), timeSlot, headerText, onTimeBlockListener) }
        }

    }

    private fun renderTimeRangeHeaderUI(textView: TextView, headerText: String) {
        textView.setBackgroundColor(Color.WHITE)
        textView.visibility = View.VISIBLE
        textView.text = headerText.substring(0, 1)
    }

    private fun renderTimeRangeItemUI(textView: TextView, timeSlot: TimeSlotUIModel, day: String, timeVenueRef: WeakReference<OnTimeBlocksListener>) {
        textView.setOnClickListener {
            timeVenueRef.get()?.OnTimeBlockClick(day = day, blockTime = timeSlot.timeBlock!!)
        }
        if (timeSlot.isAvailableBlockTime(day)) {
            textView.setBackgroundResource(R.drawable.rounded_textview)
        } else {
            textView.setBackgroundResource(R.color.colorWhite)
        }
    }

    private fun findTextViewByPosition(position: Int): TextView = with(itemView) {
        when (position) {
            0 -> tvSunday
            1 -> tvMonday
            2 -> tvTuesday
            3 -> tvWednesday
            4 -> tvThursday
            5 -> tvFriday
            else -> tvSaturday
        }

    }

}