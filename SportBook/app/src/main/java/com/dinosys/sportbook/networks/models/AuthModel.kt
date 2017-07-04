package com.dinosys.sportbook.networks.models

import com.dinosys.sportbook.configs.SKILL_ID_UNDEFINE
import com.google.gson.annotations.SerializedName
import okhttp3.Headers

data class AuthModel(@SerializedName("data") var data: AuthDataModel?,
                var header: Headers?)

data class AuthDataModel(@SerializedName("id") var id: Int?,
                         @SerializedName("email") var email: String?,
                         @SerializedName("slug") var slug: String?,
                         @SerializedName("provider") var provider: String?,
                         @SerializedName("uid") var uid: String?,
                         @SerializedName("name") var name: String?,
                         @SerializedName("nickname") var nickname: Any?,
                         @SerializedName("image") var image: Any?,
                         @SerializedName("phone_number") var phoneNumber: String?,
                         @SerializedName("skill_level") var skillLevel: String?,
                         @SerializedName("address") var address: String?,
                         @SerializedName("note") var note: Any?,
                         @SerializedName("facebook_uid") var facebookUid: String?,
                         @SerializedName("facebook_credentials") var facebookCredentials: Any?,
                         @SerializedName("skill_id") var skillId: Int? = null,
                         @SerializedName("club") var club: String?)

fun AuthDataModel.canSignUpTournament(): Boolean {
    if (this.email.isNullOrEmpty()) {
        return false
    }
    if (this.name.isNullOrEmpty()) {
        return false
    }
    if (this.phoneNumber.isNullOrEmpty()) {
        return false
    }
    if (this.address.isNullOrEmpty()) {
        return false
    }
    if (this.skillId == null || this.skillId == SKILL_ID_UNDEFINE) {
        return false
    }
    return true
}