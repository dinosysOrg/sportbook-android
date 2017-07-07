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


    fun saveInt(ctx: Context, key: String, value: Int) {
        val sharedPreference = ctx.getSharedPreferences(key, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(ctx: Context, key: String, defaultVal: Int): Int {
        val sharedPreference = ctx.getSharedPreferences(key, Context.MODE_PRIVATE)
        return sharedPreference.getInt(key, defaultVal)
    }

}