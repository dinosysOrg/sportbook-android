package com.dinosys.sportbook.features.mytournament.invitation

import android.content.Context
import com.dinosys.sportbook.R

class InvitationViewModel {

    fun getListVenue(): Array<String> = arrayOf("ABC Venue", "DEF Venue", "CVF Venue")

    fun getListTimeBlock(context: Context): Array<String> = arrayOf(context.getString(R.string.time_block_9am_12am_text), context.getString(R.string.time_block_1pm_4pm_text), context.getString(R.string.time_block_5pm_9pm_text))

    fun getInviationData(context: Context) : HashMap<String, Array<String>> {
        val hashMap = hashMapOf<String, Array<String>>()
        getListVenue().forEach { s: String ->  hashMap.put(s, getListTimeBlock(context)) }
        return hashMap
    }
    
}