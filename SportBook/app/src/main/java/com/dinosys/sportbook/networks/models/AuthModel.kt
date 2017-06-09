package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.SerializedName
import okhttp3.Headers

data class AuthModel(@SerializedName("data") var data: AuthDataModel?,
                var header: Headers?)

data class AuthDataModel(@SerializedName("id") var id: Int?,
                         @SerializedName("email") var email: String?,
                         @SerializedName("slug") var slug: String?,
                         @SerializedName("provider") var provider: String?,
                         @SerializedName("uid") var uid: String?,
                         @SerializedName("name") var name: Any?,
                         @SerializedName("nickname") var nickname: Any?,
                         @SerializedName("image") var image: Any?,
                         @SerializedName("phone_number") var phoneNumber: Any?,
                         @SerializedName("skill_level") var skillLevel: Any?,
                         @SerializedName("address") var address: Any?,
                         @SerializedName("note") var note: Any?,
                         @SerializedName("facebook_uid") var facebookUid: Any?,
                         @SerializedName("facebook_credentials") var facebookCredentials: Any?,
                         @SerializedName("skill_id") var skillId: Any?)