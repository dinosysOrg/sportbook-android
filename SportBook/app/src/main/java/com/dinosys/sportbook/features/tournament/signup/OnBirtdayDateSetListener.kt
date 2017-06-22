package com.dinosys.sportbook.features.tournament.signup

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.EditText
import com.dinosys.sportbook.extensions.editTable
import com.dinosys.sportbook.utils.DateUtil
import java.util.Calendar


class OnBirtdayDateSetListener constructor(var editText: EditText?, val patternText: String?): DatePickerDialog.OnDateSetListener {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (patternText == null) {
            clearReferences()
            return
        }

        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val date = calendar.time
        val birtDayText = DateUtil.convertDateToString(date, patternText)
        editText?.text = birtDayText.editTable

        clearReferences()
    }

    fun clearReferences() {
        editText = null
    }

}