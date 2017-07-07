package com.dinosys.sportbook.extensions

import android.app.Dialog
import android.view.WindowManager
import com.dinosys.sportbook.utils.ScreenUtil

fun Dialog.setFullWidth() {
    val width = ScreenUtil.getScreenInfo(this.context).x
    val lp = WindowManager.LayoutParams()
    lp.copyFrom(this.window!!.attributes)
    lp.width = width
    this.window!!.attributes = lp
}
