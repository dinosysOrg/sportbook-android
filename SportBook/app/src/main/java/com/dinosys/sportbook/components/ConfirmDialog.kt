package com.dinosys.sportbook.components

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.extensions.setFullWidth
import kotlinx.android.synthetic.main.dialog_confirm.*
import kotlinx.android.synthetic.main.dialog_picker.*


class ConfirmDialog(context: Context?) : Dialog(context) {

    var titleText: String? = null

    var okText: String? = null

    var cancelText: String? = null

    var onConfirmDialogListener:ConfirmDialogListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_confirm)
        initViews()
        initData()
        initListeners()
    }

    fun initViews() {
        setFullWidth()
    }

    fun initData() {
        tvTitle.text = titleText
        tvOk.text = okText
        tvCancel.text = cancelText
    }

    fun initListeners() {
        tvOk.setOnClickListener {
            onConfirmDialogListener?.onConfirmDialogCallback(dialog = this, isAccept = true)
        }
        tvCancel.setOnClickListener {
            onConfirmDialogListener?.onConfirmDialogCallback(dialog = this, isAccept = false)
        }
    }

    override fun onStop() {
        super.onStop()
        onConfirmDialogListener = null
    }

    interface ConfirmDialogListener {
        fun onConfirmDialogCallback(dialog: ConfirmDialog, isAccept: Boolean)
    }
}