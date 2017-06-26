package com.dinosys.sportbook.features.mytournament.venue

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.TimeVenue
import kotlinx.android.synthetic.main.item_my_tournament_input_time.view.*

class InputTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(timeVenueModel: TimeVenue, position: Int) = with(itemView) {
        val arrayDays = itemView.resources.getStringArray(R.array.array_time_range_days)
        if (timeVenueModel.isHeader!!) {
            tvTimeBlockLeft.visibility = View.INVISIBLE

            renderTimeRangeHeaderUI(tvSunday, arrayDays[0])
            renderTimeRangeHeaderUI(tvMonday, arrayDays[1])
            renderTimeRangeHeaderUI(tvTuesday, arrayDays[2])
            renderTimeRangeHeaderUI(tvWednesday, arrayDays[3])
            renderTimeRangeHeaderUI(tvThursday, arrayDays[4])
            renderTimeRangeHeaderUI(tvFriday, arrayDays[5])
            renderTimeRangeHeaderUI(tvSaturday, arrayDays[6])

        } else {
            tvTimeBlockLeft.visibility = View.VISIBLE
            tvTimeBlockLeft.text = timeVenueModel.timeBlock

            renderTimeRangeItemUI(tvSunday, timeVenueModel, arrayDays[0]);
            renderTimeRangeItemUI(tvMonday, timeVenueModel, arrayDays[1]);
            renderTimeRangeItemUI(tvTuesday, timeVenueModel, arrayDays[2]);
            renderTimeRangeItemUI(tvWednesday, timeVenueModel, arrayDays[3]);
            renderTimeRangeItemUI(tvThursday, timeVenueModel, arrayDays[4]);
            renderTimeRangeItemUI(tvFriday, timeVenueModel, arrayDays[5]);
            renderTimeRangeItemUI(tvSaturday, timeVenueModel, arrayDays[6]);
        }
    }

    private fun renderTimeRangeHeaderUI(textView: TextView, text: String) {
        textView.setBackgroundColor(Color.WHITE)
        textView.visibility = View.VISIBLE
        textView.text = text;
    }

    private fun renderTimeRangeItemUI(textView: TextView, timeVenueModel: TimeVenue, day: String) {
        if (timeVenueModel.isAvailableBlockTime(day)) {
            textView.setBackgroundResource(R.drawable.rounded_textview)
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.INVISIBLE
        }
    }
}