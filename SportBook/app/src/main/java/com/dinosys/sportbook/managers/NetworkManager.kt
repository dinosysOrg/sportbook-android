package com.dinosys.sportbook.managers


import android.content.Context
import android.net.ConnectivityManager

object NetworkManager {

    fun isNetworkConnected(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo ?: return false
        if (networkInfo.type == ConnectivityManager.TYPE_WIFI && networkInfo.isConnected) {
            return true
        }
        if (networkInfo.type == ConnectivityManager.TYPE_MOBILE && networkInfo.isConnected) {
            return true
        }
        return false
    }
}
