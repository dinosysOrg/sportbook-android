package com.dinosys.sportbook.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    val SHORT_PATTERN = "MM/dd/yyyy"

    @Throws(ParseException::class)
    fun convertDateToString(date: Date, pattern: String) : String {
        val simpleFormatDate = SimpleDateFormat(pattern, Locale.US)
        return simpleFormatDate.format(date)
    }

}
