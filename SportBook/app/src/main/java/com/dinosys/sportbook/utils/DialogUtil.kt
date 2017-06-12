package com.dinosys.sportbook.utils

import android.app.AlertDialog
import android.content.Context

object DialogUtil {

    fun showWarning(context:Context, title: String, content: String) {
        val builder1 = AlertDialog.Builder(context)
        builder1.setMessage(content)
        builder1.setCancelable(true)

        builder1.setPositiveButton(android.R.string.ok, { dialog, id -> dialog.cancel() })

        val alertDialog = builder1.create()
        alertDialog.show()
    }

}