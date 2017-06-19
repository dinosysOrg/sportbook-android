package com.dinosys.sportbook.features.mytournament.venue

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.networks.models.TimeVenueModel
import kotlinx.android.synthetic.main.item_my_tournament_time_venue.view.*

class TimeVenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(timeVenueModel: TimeVenueModel, position: Int) = with(itemView) {
        if (timeVenueModel.isHeader) {
            tvTimeBlockLeft.visibility = View.INVISIBLE

            tvTimeBlockRightOne.setBackgroundColor(Color.WHITE)
            tvTimeBlockRightOne.visibility = View.VISIBLE
            tvTimeBlockRightOne.text = "S";

            tvTimeBlockRightTwo.setBackgroundColor(Color.WHITE)
            tvTimeBlockRightTwo.visibility = View.VISIBLE
            tvTimeBlockRightTwo.text = "M";

            tvTimeBlockRightThree.setBackgroundColor(Color.WHITE)
            tvTimeBlockRightThree.visibility = View.VISIBLE
            tvTimeBlockRightThree.text = "T";

            tvTimeBlockRightFour.setBackgroundColor(Color.WHITE)
            tvTimeBlockRightFour.visibility = View.VISIBLE
            tvTimeBlockRightFour.text = "W";

            tvTimeBlockRightFive.setBackgroundColor(Color.WHITE)
            tvTimeBlockRightFive.visibility = View.VISIBLE
            tvTimeBlockRightFive.text = "T";

            tvTimeBlockRightSix.setBackgroundColor(Color.WHITE)
            tvTimeBlockRightSix.visibility = View.VISIBLE
            tvTimeBlockRightSix.text = "F";

            tvTimeBlockRightSeven.setBackgroundColor(Color.WHITE)
            tvTimeBlockRightSeven.visibility = View.VISIBLE
            tvTimeBlockRightSeven.text = "S";

        } else {
            tvTimeBlockLeft.visibility = View.VISIBLE
            tvTimeBlockLeft.text = timeVenueModel.time_block
            if (timeVenueModel.isDayOneAvailable) {
                tvTimeBlockRightOne.setBackgroundResource(R.drawable.rounded_textview)
                tvTimeBlockRightOne.visibility = View.VISIBLE
            } else {
                tvTimeBlockRightOne.visibility = View.INVISIBLE
            }

            if (timeVenueModel.isDayTwoAvailable) {
                tvTimeBlockRightTwo.setBackgroundResource(R.drawable.rounded_textview)
                tvTimeBlockRightTwo.visibility = View.VISIBLE
            } else {
                tvTimeBlockRightTwo.visibility = View.INVISIBLE
            }

            if (timeVenueModel.isDayThreeAvailable) {
                tvTimeBlockRightThree.setBackgroundResource(R.drawable.rounded_textview)
                tvTimeBlockRightThree.visibility = View.VISIBLE
            } else {
                tvTimeBlockRightThree.visibility = View.INVISIBLE
            }

            if (timeVenueModel.isDayFourAvailable) {
                tvTimeBlockRightFour.setBackgroundResource(R.drawable.rounded_textview)
                tvTimeBlockRightFour.visibility = View.VISIBLE
            } else {
                tvTimeBlockRightFour.visibility = View.INVISIBLE
            }

            if (timeVenueModel.isDayFiveAvailable) {
                tvTimeBlockRightFive.setBackgroundResource(R.drawable.rounded_textview)
                tvTimeBlockRightFive.visibility = View.VISIBLE
            } else {
                tvTimeBlockRightFive.visibility = View.INVISIBLE
            }

            if (timeVenueModel.isDaySixAvailable) {
                tvTimeBlockRightSix.setBackgroundResource(R.drawable.rounded_textview)
                tvTimeBlockRightSix.visibility = View.VISIBLE
            } else {
                tvTimeBlockRightSix.visibility = View.INVISIBLE
            }

            if (timeVenueModel.isDaySevenAvailable) {
                tvTimeBlockRightSeven.setBackgroundResource(R.drawable.rounded_textview)
                tvTimeBlockRightSeven.visibility = View.VISIBLE
            } else {
                tvTimeBlockRightSeven.visibility = View.INVISIBLE
            }

        }
    }


}