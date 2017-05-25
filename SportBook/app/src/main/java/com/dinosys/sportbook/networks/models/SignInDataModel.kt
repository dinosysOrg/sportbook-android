package com.dinosys.sportbook.networks.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignInDataModel {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("provider")
    @Expose
    var provider: String? = null
    @SerializedName("uid")
    @Expose
    var uid: String? = null
    @SerializedName("name")
    @Expose
    var name: Any? = null
    @SerializedName("nickname")
    @Expose
    var nickname: Any? = null
    @SerializedName("image")
    @Expose
    var image: Any? = null
    @SerializedName("phone_number")
    @Expose
    var phoneNumber: Any? = null
    @SerializedName("skill_level")
    @Expose
    var skillLevel: Any? = null
    @SerializedName("address")
    @Expose
    var address: Any? = null
    @SerializedName("note")
    @Expose
    var note: Any? = null
    @SerializedName("facebook_uid")
    @Expose
    var facebookUid: Any? = null
    @SerializedName("facebook_credentials")
    @Expose
    var facebookCredentials: Any? = null
    @SerializedName("skill_id")
    @Expose
    var skillId: Any? = null

}
