package com.dinosys.sportbook.components

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.dinosys.sportbook.R
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.toolbar.view.*


class ToolbarView : RelativeLayout {

    var enableBackIcon = false

    var enableNotificationIcon = false

    var toolbarTitle = ""

    constructor(context: Context) : super(context) {
        parseAttrs(null)
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        parseAttrs(attrs)
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        parseAttrs(attrs)
        initViews()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        parseAttrs(attrs)
        initViews()
    }

    fun initViews() {
        inflate(this.context, R.layout.toolbar, this)

        tvNotificationIcon.visibility = if (enableNotificationIcon) View.VISIBLE else View.GONE
        tvBackIcon.visibility = if (enableBackIcon)  View.VISIBLE else View.GONE
        tvTitle.text = toolbarTitle
    }

    fun parseAttrs(attrs: AttributeSet?) {
        if (attrs != null) {
            val values = context.obtainStyledAttributes(attrs, R.styleable.ToolbarView)
            enableBackIcon = values.getBoolean(R.styleable.ToolbarView_enableBackIcon, false)
            enableNotificationIcon = values.getBoolean(R.styleable.ToolbarView_enableNotificationIcon, false)
            toolbarTitle = values.getString(R.styleable.ToolbarView_toolbarTitle)
            values.recycle()
        }
    }

}
