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
import kotlinx.android.synthetic.main.dialog_picker.*


class PickerDialog(context: Context?) : Dialog(context) {

    var displayedValues: Array<String>? = null

    var title: String? =  null

    var selectedIndex: Int = 0

    var pickerDialogListener: PickerDialogListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_picker)
        setFullWidth()
        initData()
        initListeners()
    }

    private fun setFullWidth() {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width: Int

        if (Build.VERSION.SDK_INT > VERSION_CODES.FROYO) {
            width = manager.defaultDisplay.width
        } else {
            val point = Point()
            manager.defaultDisplay.getSize(point)
            width = point.x
        }

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(this.window!!.attributes)
        lp.width = width

        this.window!!.attributes = lp
    }

    private fun initData() {
        numberPicker.setDisplayedValues(displayedValues)
        numberPicker.minValue = 0
        numberPicker.maxValue = displayedValues?.size?.minus(1)!!
        tvDialogPickerTitle.setText(title)
    }

    private fun initListeners() {
        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            selectedIndex = newVal
        }

        tvDialogPickerCancel.setOnClickListener {
            this.dismiss()
        }

        tvDialogPickerOk.setOnClickListener {
            if (pickerDialogListener != null) {
                pickerDialogListener!!.onItemSelected(selectedIndex, displayedValues!!.get(selectedIndex))
                dismiss()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        pickerDialogListener = null
    }

    interface PickerDialogListener {
        fun onItemSelected(index: Int, string: String)
    }
}