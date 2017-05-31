package com.dinosys.sportbook.extensions

import com.dinosys.sportbook.configs.PASSWORD_MAX_LENGHT_REQUIRED

val  String.isInvalidEmail: Boolean  get() =  !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})").containsMatchIn(this)

val  String.isInvalidPassword: Boolean  get() = (this.length < PASSWORD_MAX_LENGHT_REQUIRED)

val  String.throwable: Throwable get() = Throwable(this)
