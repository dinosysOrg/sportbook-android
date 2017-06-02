package com.dinosys.sportbook.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences



object SPUtil {

    fun saveString(ctx: Context, key: String, value: String) {
        val spPreferences = ctx.getSharedPreferences(key, Context.MODE_PRIVATE)
        val editor = spPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(ctx: Context, key: String, defaultVal: String): String {
        val spPreferences = ctx.getSharedPreferences(key, Context.MODE_PRIVATE)
        return spPreferences.getString(key, defaultVal)
    }

}