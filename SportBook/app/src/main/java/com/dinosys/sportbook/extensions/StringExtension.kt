package com.dinosys.sportbook.extensions

import android.content.Context
import android.text.Editable
import com.dinosys.sportbook.configs.PASSWORD_MAX_LENGHT_REQUIRED
import java.io.IOException

val  String.isInvalidEmail: Boolean  get() =  !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})").containsMatchIn(this)

val  String.isInvalidPassword: Boolean  get() = (this.length < PASSWORD_MAX_LENGHT_REQUIRED)

val  String.throwable: Throwable get() = Throwable(this)

val  String.editTable: Editable? get() = Editable.Factory.getInstance().newEditable(this)

@Throws(IOException::class)
fun String.Companion.readJsonFromAsset(context: Context, fileJsonPath: String?): String  {
    val inputStream = context.getAssets().open(fileJsonPath)

    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    val json = String(buffer)

    return json
}