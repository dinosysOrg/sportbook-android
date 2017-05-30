package com.dinosys.sportbook.utils

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View

object ContextUtil {

    fun getApplicationContext(fragment: Fragment?): Context? {
        return getApplicationContext(fragment?.activity)
    }

    fun getApplicationContext(view: View?): Context? {
        return getApplicationContext(view?.context)
    }

    fun getApplicationContext(context: Context?): Context? {
        return context?.applicationContext
    }

    fun getApplicationContext(activity: Activity?): Context? {
        return activity?.applicationContext
    }

}
