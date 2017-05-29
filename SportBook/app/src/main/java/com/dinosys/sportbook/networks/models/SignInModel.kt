package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.Headers

class SignInModel {

    @SerializedName("data")
    @Expose
    var data: SignInDataModel? = null

    var header: Headers? = null
}
