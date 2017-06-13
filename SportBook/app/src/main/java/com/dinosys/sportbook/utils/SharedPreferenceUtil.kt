package com.dinosys.sportbook.utils

import android.content.Context


object SharedPreferenceUtil {

    fun saveString(ctx: Context, key: String, value: String) {
        val sharedPreference = ctx.getSharedPreferences(key, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(ctx: Context, key: String, defaultVal: String): String {
        val sharedPreference = ctx.getSharedPreferences(key, Context.MODE_PRIVATE)
        return sharedPreference.getString(key, defaultVal)
    }

}