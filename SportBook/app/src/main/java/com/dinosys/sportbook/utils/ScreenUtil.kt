package com.dinosys.sportbook.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager


object ScreenUtil {

    fun getScreenInfo(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            val width = windowManager.defaultDisplay.width
            val height = windowManager.defaultDisplay.height
            return Point(width, height)
        } else {
            val point = Point()
            windowManager.defaultDisplay.getSize(point)
            return point
        }
    }

}