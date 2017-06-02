package com.dinosys.sportbook.extensions

import android.content.Context
import android.support.v4.app.Fragment
import com.dinosys.sportbook.configs.KEY_USER_INFO
import com.dinosys.sportbook.networks.models.AuthDataModel
import com.dinosys.sportbook.networks.models.AuthModel
import com.dinosys.sportbook.utils.SPUtil
import okhttp3.Headers
import org.json.JSONObject

val Fragment?.appContext: Context? get() = this?.activity?.applicationContext

fun Fragment.saveUser(ctx: Context, auth: AuthModel) {
    val header = auth.header
    val data = auth.data

    val dataObj = JSONObject()
    val authObj = JSONObject()

    authObj.put("id", data?.id)
    authObj.put("email", data?.email)
    authObj.put("slug", data?.slug)
    authObj.put("provider", data?.provider)
    authObj.put("uid", data?.uid)
    authObj.put("name", data?.name ?: "")
    authObj.put("nickname", data?.nickname ?: "")
    authObj.put("image", data?.image ?: "")
    authObj.put("phone_number", data?.phoneNumber ?: "")
    authObj.put("skill_level", data?.skillLevel ?: "")
    authObj.put("address", data?.address ?: "")
    authObj.put("note", data?.note ?: "")
    authObj.put("facebook_uid", data?.facebookUid ?: "")
    authObj.put("facebook_credentials", data?.facebookCredentials ?: "")
    authObj.put("skill_id", data?.skillId ?: "")

    val headerObj = JSONObject()
    headerObj.put("Access-Token", header?.get("Access-Token"))
    headerObj.put("Client", header?.get("Client"))
    headerObj.put("Expiry", header?.get("Expiry"))
    headerObj.put("Token-Type", header?.get("Token-Type"))
    headerObj.put("Uid", header?.get("Uid"))

    dataObj.put("auth", authObj)
    dataObj.put("header", headerObj)

    SPUtil.saveString(ctx, KEY_USER_INFO, dataObj.toString())
}

fun Fragment.getUser(ctx: Context): AuthModel? {
    val data = SPUtil.getString(ctx, KEY_USER_INFO, "")
    if (data.isEmpty()) {
        return null
    }
    val dataObj = JSONObject(data)

    val authObj = dataObj.getJSONObject("auth")
    val headerObj = dataObj.getJSONObject("header")

    val authData = AuthDataModel(
            authObj.getInt("id"),
            authObj.getString("email"),
            authObj.getString("slug"),
            authObj.getString("provider"),
            authObj.getString("uid"),
            authObj.get("name") ?: "",
            authObj.get("nickname") ?: "",
            authObj.get("image") ?: "",
            authObj.get("phone_number") ?: "",
            authObj.get("skill_level") ?: "",
            authObj.get("address") ?: "",
            authObj.get("note") ?: "",
            authObj.get("facebook_uid") ?: "",
            authObj.get("facebook_credentials") ?: "",
            authObj.get("skill_id") ?: ""
    )
    val map = mapOf<String, String>(
            "Access-Token" to headerObj.getString("Access-Token"),
            "Client" to headerObj.getString("Client"),
            "Expiry" to headerObj.getString("Expiry"),
            "Token-Type" to headerObj.getString("Token-Type"),
            "Uid" to headerObj.getString("Uid")
    )
    return AuthModel(authData, Headers.of(map))
}