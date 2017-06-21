package com.dinosys.sportbook.features.signin

import android.content.Context
import com.dinosys.sportbook.R
import com.dinosys.sportbook.configs.PASSWORD_MAX_LENGHT_REQUIRED
import com.dinosys.sportbook.configs.REDIRECT_URL_FORGOT_PASSWORD
import com.dinosys.sportbook.extensions.isInvalidEmail
import com.dinosys.sportbook.extensions.isInvalidPassword
import com.dinosys.sportbook.extensions.throwable
import com.dinosys.sportbook.networks.authentication.AuthenticationAPI
import com.dinosys.sportbook.networks.models.AuthModel
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class SignInViewModel @Inject constructor(val authApi : AuthenticationAPI) {

     fun signIn(context: Context?, userName:String?, password:String?) : Observable<Response<AuthModel>> {
        if (userName.isNullOrEmpty()) {
            return Observable.error(context?.getString(R.string.error_email_required_text)?.throwable)
        }
        if (password.isNullOrEmpty()) {
            return Observable.error(context?.getString(R.string.error_password_required_text)?.throwable)
        }
        if (userName!!.isInvalidEmail) {
            return Observable.error(context?.getString(R.string.error_username_invalid_text)?.throwable)
        }
        if (password!!.isInvalidPassword) {
            return Observable.error(context?.getString(R.string.error_password_invalid_text,
                                        PASSWORD_MAX_LENGHT_REQUIRED)?.throwable)
        }
        return authApi.signIn(userName, password)
    }

    fun signInWithFacebook(token:String) = authApi.signInWithFacebook(token)

    fun forgotPassword(context:Context?, email:String?):Observable<Response<JSONObject>> {
        if (email.isNullOrEmpty()) {
            return Observable.error(context?.getString(R.string.error_email_required_text)?.throwable)
        }
        if (email!!.isInvalidEmail) {
            return Observable.error(context?.getString(R.string.error_username_invalid_text)?.throwable)
        }
        return authApi.forgotPassword(email, REDIRECT_URL_FORGOT_PASSWORD)
    }

    fun sendTokenToServer(userId: Int?,
                          token: String?,
                          platform: Int?): Observable<Response<JSONObject>> {
        return authApi.sendTokenToServer(userId, token, platform)
    }
}



