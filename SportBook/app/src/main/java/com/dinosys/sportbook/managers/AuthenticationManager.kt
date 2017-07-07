package com.dinosys.sportbook.managers

import android.content.Context
import com.dinosys.sportbook.configs.KEY_HEADER_INFO
import com.dinosys.sportbook.configs.KEY_USER_INFO
import com.dinosys.sportbook.networks.models.AuthDataModel
import com.dinosys.sportbook.networks.models.AuthModel
import com.dinosys.sportbook.utils.SharedPreferenceUtil
import okhttp3.Headers
import org.json.JSONObject

object AuthenticationManager {

    fun saveAuthenticationInfo(context: Context, authModel: AuthModel?) {
        saveUserInfo(context, authModel?.data)
        saveHeaderInfo(context, authModel?.header)
    }

    fun getUserInfo(context: Context): AuthDataModel? {
        val data = SharedPreferenceUtil.getString(context, KEY_USER_INFO, "")
        if (data.isEmpty()) {
            return null
        }
        val authObj = JSONObject(data)

        val authData = AuthDataModel(
                id = authObj.getInt("id"),
                email = authObj.getString("email"),
                slug = authObj.getString("slug"),
                provider = authObj.getString("provider"),
                uid = authObj.getString("uid"),
                name = authObj.getString("name") ?: "",
                nickname = authObj.get("nickname") ?: "",
                image = authObj.get("image") ?: "",
                phoneNumber = authObj.getString("phone_number") ?: "",
                skillLevel = authObj.getString("skill_level") ?: "",
                address = authObj.getString("address") ?: "",
                note = authObj.getString("note") ?: "",
                facebookUid = authObj.getString("facebook_uid") ?: "",
                facebookCredentials = authObj.get("facebook_credentials") ?: "",
                skillId = authObj.getInt("skill_id"),
                club = authObj.getString("club") ?: ""
        )
        return authData
    }

    fun getHeaderInfo(context: Context): Headers? {
        val headerText = SharedPreferenceUtil.getString(context, KEY_HEADER_INFO, "")
        if (headerText.isEmpty()) {
            return null
        }
        val headerObj = JSONObject(headerText)

        val map = mapOf<String, String>(
                "Access-Token" to headerObj.getString("Access-Token"),
                "Client" to headerObj.getString("Client"),
                "Expiry" to headerObj.getString("Expiry"),
                "Token-Type" to headerObj.getString("Token-Type"),
                "Uid" to headerObj.getString("Uid")
        )
        return Headers.of(map)
    }

    fun saveHeaderInfo(context: Context, header: Headers?) {
        val headerObj = JSONObject()

        headerObj.put("Access-Token", header?.get("Access-Token"))
        headerObj.put("Client", header?.get("Client"))
        headerObj.put("Expiry", header?.get("Expiry"))
        headerObj.put("Token-Type", header?.get("Token-Type"))
        headerObj.put("Uid", header?.get("Uid"))

        SharedPreferenceUtil.saveString(context, KEY_HEADER_INFO, headerObj.toString())
    }

    fun saveUserInfo(context: Context, data: AuthDataModel?) {
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
        authObj.put("skill_id", data?.skillId)
        authObj.put("club", data?.club ?: "")

        SharedPreferenceUtil.saveString(context, KEY_USER_INFO, authObj.toString())
    }

}

