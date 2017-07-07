package com.dinosys.sportbook.utils

import BuildConfig
import android.util.Log

object LogUtil {

    fun d(tag: String, message: String) {
        if (BuildConfig.LOG_DEBUG_ENABLE) {
            Log.d(tag, message)
        }
    }

    fun e(tag: String, message: String) {
        if (BuildConfig.LOG_ERROR_ENABLE) {
            Log.e(tag, message)
        }
    }

}