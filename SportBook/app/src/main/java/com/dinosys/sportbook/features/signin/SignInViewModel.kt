package com.dinosys.sportbook.features.signin

import android.content.Context
import com.dinosys.sportbook.R
import com.dinosys.sportbook.configs.PASSWORD_MAX_LENGHT_REQUIRED
import com.dinosys.sportbook.extensions.isInvalidEmail
import com.dinosys.sportbook.extensions.isInvalidPassword
import com.dinosys.sportbook.extensions.throwable
import com.dinosys.sportbook.networks.authentication.AuthenticationAPI
import com.dinosys.sportbook.networks.models.SignInModel
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInViewModel @Inject constructor(val authApi : AuthenticationAPI) {

     fun signIn(context: Context?, userName:String?, password:String?) : Observable<Response<SignInModel>> {
        if (userName.isNullOrEmpty()) {
            return Observable.error(context?.getString(R.string.error_username_required_text)?.throwable)
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
}



