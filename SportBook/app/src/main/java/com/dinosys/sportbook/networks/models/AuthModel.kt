package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName
import okhttp3.Headers

data class AuthModel(@SerializedName("data") var data: AuthDataModel?,
                var header: Headers?)
