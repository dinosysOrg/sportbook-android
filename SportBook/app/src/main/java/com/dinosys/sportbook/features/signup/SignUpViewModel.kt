package com.dinosys.sportbook.features.signup

import android.content.Context
import com.dinosys.sportbook.R
import com.dinosys.sportbook.configs.PASSWORD_MAX_LENGHT_REQUIRED
import com.dinosys.sportbook.exceptions.*
import com.dinosys.sportbook.extensions.isInvalidEmail
import com.dinosys.sportbook.extensions.isInvalidPassword
import com.dinosys.sportbook.extensions.throwable
import com.dinosys.sportbook.networks.authentication.AuthenticationAPI
import com.dinosys.sportbook.networks.models.AuthModel
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


class SignUpViewModel @Inject constructor(val authApi: AuthenticationAPI) {

    fun signUp(context: Context?, email: String?, password: String?, confirmPassword: String?): Observable<Response<AuthModel>> {
        if (email.isNullOrEmpty()) {
            return Observable.error(SignUpEmailNullOrEmptyException(context?.getString(R.string.error_username_required_text)))
        }
        if (password.isNullOrEmpty()) {
            return Observable.error(SignUpPasswordNullOrEmptyException(context?.getString(R.string.error_password_required_text)))
        }
        if (email!!.isInvalidEmail) {
            return Observable.error(SignUpEmailInvalidException(context?.getString(R.string.error_username_invalid_text)))
        }
        if (password!!.isInvalidPassword) {
            return Observable.error(SignUpPasswordInvalidException(context?.getString(R.string.error_password_invalid_text, PASSWORD_MAX_LENGHT_REQUIRED)))
        }
        if (confirmPassword!!.isNullOrEmpty()) {
            return Observable.error(SignUpPasswordConfirmINullOrEmptyException(context?.getString(R.string.error_password_required_text)))
        }
        if (confirmPassword.isInvalidPassword) {
            return Observable.error(SignUpPasswordConfirmInvalidException(context?.getString(R.string.error_password_invalid_text, PASSWORD_MAX_LENGHT_REQUIRED)))
        }
        if(password != confirmPassword){
            return Observable.error(SignUpPasswordNotMatchException(context?.getString(R.string.error_password_mismatch_text)))
        }
        return authApi.signUp(email, password, confirmPassword)
    }
}