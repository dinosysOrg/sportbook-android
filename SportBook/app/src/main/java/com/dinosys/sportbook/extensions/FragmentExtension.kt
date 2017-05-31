package com.dinosys.sportbook.extensions

import android.content.Context
import android.support.v4.app.Fragment

val Fragment?.appContext: Context? get() = this?.activity?.applicationContext