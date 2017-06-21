package com.dinosys.sportbook.features.signup

import android.content.Context
import com.dinosys.sportbook.R
import com.dinosys.sportbook.configs.PASSWORD_MAX_LENGHT_REQUIRED
import com.dinosys.sportbook.extensions.isInvalidEmail
import com.dinosys.sportbook.extensions.isInvalidPassword
import com.dinosys.sportbook.extensions.throwable
import com.dinosys.sportbook.networks.authentication.AuthenticationAPI
import com.dinosys.sportbook.networks.models.AuthModel
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpViewModel @Inject constructor(val authApi: AuthenticationAPI) {

    fun signUp(context: Context?, name: String?, email: String?, password: String?, confirmpassword: String?): Observable<Response<AuthModel>> {
        if (name.isNullOrEmpty()) {
            return Observable.error(context?.getString(R.string.error_name_required_text)?.throwable)
        }
        if (email.isNullOrEmpty()) {
            return Observable.error(context?.getString(R.string.error_username_required_text)?.throwable)
        }
        if (password.isNullOrEmpty()) {
            return Observable.error(context?.getString(R.string.error_password_required_text)?.throwable)
        }
        if (email!!.isInvalidEmail) {
            return Observable.error(context?.getString(R.string.error_username_invalid_text)?.throwable)
        }
        if (password!!.isInvalidPassword) {
            return Observable.error(context?.getString(R.string.error_password_invalid_text,
                    PASSWORD_MAX_LENGHT_REQUIRED)?.throwable)
        }
        if (confirmpassword!!.isInvalidPassword) {
            return Observable.error(context?.getString(R.string.error_password_invalid_text,
                    PASSWORD_MAX_LENGHT_REQUIRED)?.throwable)
        }
        if(!password.equals(confirmpassword)){
            return Observable.error(context?.getString(R.string.error_password_mismatch_text)?.throwable)
        }
        return authApi.signUp(email, password)
    }
}