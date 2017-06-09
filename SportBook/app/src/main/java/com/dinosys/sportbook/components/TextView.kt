package com.dinosys.sportbook.components

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.graphics.Typeface
import com.dinosys.sportbook.R


class TextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
       parseAttributes(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
       parseAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
       parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        val typeface: Int
        if (attrs == null) { //Not created from xml
            typeface = Font.FONT_AWESOME
        } else {
            val values = context.obtainStyledAttributes(attrs, R.styleable.TextView)
            typeface = values.getInt(R.styleable.TextView_typeface, Font.FONT_AWESOME)
            values.recycle()
        }
        setTypeface(getFont(context, typeface))
    }

    fun getFont(context: Context, typeface: Int): Typeface {
        if (Font.fontAwesome == null) {
            Font.fontAwesome = Typeface.createFromAsset(context.assets, "fonts/fontawesome-webfont.ttf")
        }
        return Font.fontAwesome!!
    }

    object Font {
        val FONT_AWESOME = 0
        var fontAwesome: Typeface? = null
    }
}