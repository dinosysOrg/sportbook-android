package com.dinosys.sportbook.extensions

import android.os.Build
import android.text.Html
import android.widget.TextView
import com.dinosys.sportbook.components.URLImageParser

fun TextView.loadHtmlToText(source: String?) {
    if (source == null)
        return
    val urlImageParser = URLImageParser(context, this)
    if (Build.VERSION.SDK_INT >= 24) {
        this.text = Html.fromHtml(source,
                Html.FROM_HTML_MODE_LEGACY, urlImageParser, null)
    } else {
        this.text = Html.fromHtml(source, urlImageParser, null)
    }
}