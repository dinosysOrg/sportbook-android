package com.dinosys.sportbook.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(url:String?) {
    Glide.with(this.context).load(url).into(this)
}