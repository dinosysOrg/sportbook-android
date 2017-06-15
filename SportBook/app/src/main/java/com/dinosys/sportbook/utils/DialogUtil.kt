package com.dinosys.sportbook.utils

import android.app.AlertDialog
import android.content.Context

object DialogUtil {

    fun showWarning(context:Context, title: String, content: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(content)
        builder.setCancelable(true)

        builder.setPositiveButton(android.R.string.ok, { dialog, id -> dialog.cancel() })

        val alertDialog = builder.create()
        alertDialog.show()
    }

}